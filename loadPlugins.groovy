/*
curl -d "script=$(cat loadPlugins.groovy)" -v http://localhost:10001/scriptText
*/
import jenkins.model.*
import java.util.logging.Logger
def logger = Logger.getLogger("")
def installed = false
def initialized = false
//def plgns = ["docker-workflow", "ant", "build-timeout", "credentials-binding", "email-ext", "github-organization-folder", "gradle", "workflow-aggregator", "ssh-slaves", "subversion", "timestamper", "ws-cleanup"]
def plgns = ["jsch", "ws-cleanup", "blueocean-commons", "mercurial", "structs", "jira", "sse-gateway", "apache-httpcomponents-client-4-api", "subversion", "pipeline-model-extensions", "workflow-aggregator", "mailer", "git", "handy-uri-templates-2-api", "blueocean-jira", "command-launcher", "workflow-api", "workflow-job", "ssh-credentials", "authentication-tokens", "blueocean-rest-impl", "github-branch-source", "htmlpublisher", "workflow-cps-global-lib", "blueocean-web", "jackson2-api", "ssh-slaves", "docker-workflow", "pipeline-stage-tags-metadata", "blueocean-pipeline-scm-api", "pipeline-milestone-step", "credentials", "blueocean-executor-info", "cloudbees-bitbucket-branch-source", "github", "lockable-resources", "jquery-detached", "blueocean-personalization", "workflow-scm-step", "matrix-auth", "matrix-project", "pipeline-stage-step", "pipeline-build-step", "antisamy-markup-formatter", "pipeline-input-step", "ant", "bouncycastle-api", "handlebars", "blueocean", "pipeline-github-lib", "variant", "momentjs", "blueocean-jwt", "plain-credentials", "docker-commons", "git-client", "timestamper", "gradle", "pipeline-rest-api", "workflow-basic-steps", "github-api", "blueocean-i18n", "ldap", "blueocean-events", "blueocean-core-js", "blueocean-config", "blueocean-github-pipeline", "credentials-binding", "pipeline-model-definition", "pipeline-stage-view", "token-macro", "blueocean-display-url", "workflow-multibranch", "script-security", "git-server", "pipeline-model-declarative-agent", "workflow-step-api", "pipeline-graph-analysis", "blueocean-git-pipeline", "pipeline-model-api", "jenkins-design-language", "workflow-cps", "blueocean-autofavorite", "workflow-durable-task-step", "email-ext", "branch-api", "jdk-tool", "cloudbees-folder", "blueocean-pipeline-editor", "blueocean-dashboard", "durable-task", "junit", "pam-auth", "pubsub-light", "scm-api", "blueocean-pipeline-api-impl", "ace-editor", "display-url-api", "workflow-support", "resource-disposer", "blueocean-rest", "build-timeout", "favorite", "blueocean-bitbucket-pipeline", "mapdb-api","role-strategy","matrix-auth"]
println(plgns)
def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()
plgns.each {
  println(it)
  if (!pm.getPlugin(it)) {
    println("Looking UpdateCenter for " + it)
    if (!initialized) {
      uc.updateAllSites()
      initialized = true
    }
    def plugin = uc.getPlugin(it)
    if (plugin) {
      println("Installing " + it)
    	def installFuture = plugin.deploy()
      while(!installFuture.isDone()) {
        println("Waiting for plugin install: " + it)
        sleep(3000)
      }
      installed = true
    }
  }
}
if (installed) {
  println("Plugins installed, initializing a restart!")
  instance.save()
  instance.restart()
}
