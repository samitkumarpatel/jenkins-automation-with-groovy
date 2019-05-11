/*
curl -d "script=$(cat security.groovy)" -v http://localhost:10001/scriptText
*/
import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

// create user with full access
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin","admin")
hudsonRealm.createAccount("admin1","admin1")
instance.setSecurityRealm(hudsonRealm)

//FullControlOnceLoggedInAuthorizationStrategy 
def strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

//GlobalMatrixAuthorizationStrategy
def s = new hudson.security.GlobalMatrixAuthorizationStrategy()
s.add(Jenkins.ADMINISTER, "admin")
s.add(Jenkins.ADMINISTER, "admin1")
instance.setAuthorizationStrategy(s)


instance.save()