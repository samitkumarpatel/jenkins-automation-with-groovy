// This will copy a job from job newman-poc,already available in Jenkins
def inputStream = new FileInputStream(new File(System.getenv("HOME")+"/jobs/newman-poc/config.xml"))
Jenkins.getInstance().createProjectFromXML("test01",inputStream)
