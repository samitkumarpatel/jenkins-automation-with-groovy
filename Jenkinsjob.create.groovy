import jenkins.model.*

def jobName = "my-new-job"
def configXml = "" // your xml goes here

def xmlStream = new ByteArrayInputStream( configXml.getBytes() )

Jenkins.instance.createProjectFromXML(jobName, xmlStream)

//https://javadoc.jenkins-ci.org/jenkins/model/Jenkins.html  -for api reference
