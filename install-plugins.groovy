/*
curl -d "script=$(cat loadPlugins.groovy)" -v http://localhost:10001/scriptText
curl --data-urlencode "script=$(< ./loadPlugins.groovy)" http://localhost:10001/scriptText
*/
import jenkins.model.*
import java.util.logging.Level
import java.util.logging.Logger
def logger = Logger.getLogger("install-plugins")
def installed = false
def initialized = false
def other_plugins = ["jsch", "ws-cleanup", "blueocean-commons", "mercurial", "structs", "jira", "sse-gateway", "apache-httpcomponents-client-4-api", "subversion", "pipeline-model-extensions", "workflow-aggregator", "mailer", "git", "handy-uri-templates-2-api", "blueocean-jira", "command-launcher", "workflow-api", "workflow-job", "ssh-credentials", "authentication-tokens", "blueocean-rest-impl", "github-branch-source", "htmlpublisher", "workflow-cps-global-lib", "blueocean-web", "jackson2-api", "ssh-slaves", "docker-workflow", "pipeline-stage-tags-metadata", "blueocean-pipeline-scm-api", "pipeline-milestone-step", "credentials", "blueocean-executor-info", "cloudbees-bitbucket-branch-source", "github", "lockable-resources", "jquery-detached", "blueocean-personalization", "workflow-scm-step", "matrix-auth", "matrix-project", "pipeline-stage-step", "pipeline-build-step", "antisamy-markup-formatter", "pipeline-input-step", "ant", "bouncycastle-api", "handlebars", "blueocean", "pipeline-github-lib", "variant", "momentjs", "blueocean-jwt", "plain-credentials", "docker-commons", "git-client", "timestamper", "gradle", "pipeline-rest-api", "workflow-basic-steps", "github-api", "blueocean-i18n", "ldap", "blueocean-events", "blueocean-core-js", "blueocean-config", "blueocean-github-pipeline", "credentials-binding", "pipeline-model-definition", "pipeline-stage-view", "token-macro", "blueocean-display-url", "workflow-multibranch", "script-security", "git-server", "pipeline-model-declarative-agent", "workflow-step-api", "pipeline-graph-analysis", "blueocean-git-pipeline", "pipeline-model-api", "jenkins-design-language", "workflow-cps", "blueocean-autofavorite", "workflow-durable-task-step", "email-ext", "branch-api", "jdk-tool", "cloudbees-folder", "blueocean-pipeline-editor", "blueocean-dashboard", "durable-task", "junit", "pam-auth", "pubsub-light", "scm-api", "blueocean-pipeline-api-impl", "ace-editor", "display-url-api", "workflow-support", "resource-disposer", "blueocean-rest", "build-timeout", "favorite", "blueocean-bitbucket-pipeline", "mapdb-api","role-strategy","matrix-auth"]
def plugins = [
        "cloudbees-folder",
        "antisamy-markup-formatter",
        "build-timeout",
        "credentials-binding",
        "timestamper",
        "ws-cleanup",
        "ant",
        "gradle",
        "workflow-aggregator",
        "github-branch-source",
        "pipeline-github-lib",
        "pipeline-stage-view",
        "git",
        "subversion",
        "ssh-slaves",
        "matrix-auth",
        "pam-auth",
        "ldap",
        "email-ext",
        "mailer"
]

def instance = Jenkins.getInstance()
def pluginsManager = instance.getPluginManager()
def updateCenter = instance.getUpdateCenter()
plugins.each {
    logger.log(Level.INFO,"{0} plugins installation triggered",it)
    if (!pluginsManager.getPlugin(it)) {
        logger.log(Level.INFO,"Looking UpdateCenter for : {0}",it)
        if (!initialized) {
            updateCenter.updateAllSites()
            initialized = true
        }
        def plugin = updateCenter.getPlugin(it)
        if (plugin) {
            logger.info("Installing - "+it)
            def installFuture = plugin.deploy()
            while(!installFuture.isDone()) {
                logger.log(Level.INFO,"Waiting for {0} plugin to be install", it)
                sleep(3000)
            }
            installed = true
        }
    }
}
if (installed) {
    logger.info("Plugins installed!, initializing a restart!")
    instance.save()
    instance.restart()
}
