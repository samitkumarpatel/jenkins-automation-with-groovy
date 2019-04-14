/*
curl -d "script=$(cat executors.groovy)" -v http://localhost:10001/scriptText
*/
import jenkins.model.*
Jenkins.instance.setNumExecutors(3)