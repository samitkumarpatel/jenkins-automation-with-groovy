# Jenkins Automation

### Execute a groovy script remotely
* see [help](https://support.cloudbees.com/hc/en-us/articles/217509228-execute-groovy-with-a-rest-call)

* groovyscript [example](https://github.com/cloudbees/jenkins-scripts)

```
curl -d "script=$(cat security.groovy)" -v http://localhost:10001/scriptText

curl -d "script=println 'this script works'" -v --user username:ApiToken http://localhost:8080/scriptText

```

### Rest Api