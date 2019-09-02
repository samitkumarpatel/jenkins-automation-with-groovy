# SECRET_TEXT - this can be get from credential.xml and it looks like {LOTS_OF_LETTER_NUMBER==}

println(hudson.util.Secret.decrypt("SECRET_TEXT"))

println(hudson.util.Secret.fromString("SECRET_TEXT").getPlainText())

println(hudson.util.Secret.fromString("PASSWORD").getEncryptedValue())

# Do that in a sketchy way with pipeline - This need Jenkins Credential Plugins to be installed on Jenkins

#Print username and decrypted password:
  
node('master') {
  stage('Jenkins Credentials | Decrypt Password') {
    withCredentials([usernamePassword(credentialsId: 'maerskdev_net',
                                      passwordVariable: 'password',
                                      usernameVariable: 'username')]) {
      creds = "\nUsername: ${username}\nPassword: ${password}\n"
    }
    println creds
  }
}

#Show the contents of the secret file:
node {
  stage('Jenkins Credentials | Decrypt Secret File') {
    withCredentials([file(credentialsId: 'credentials-id',
                          variable: 'secretFile')]) {
      sh "cat ${secretFile}"
    }
  }
}

#Show decrypted secret text string (e.g. API key):
node {
  stage('Jenkins Credentials | Decrypt Secret Text String') {
    withCredentials([string(credentialsId: 'credentials-id',
                            variable: 'secretText')]) {
      apiKey = "\nAPI key: ${secretText}\n"
    }
    println apiKey
  }
}

#Get SSH username and a private key:
node {
  stage('Jenkins Credentials | Decrypt SSH key') {
    withCredentials([sshUserPrivateKey(credentialsId: 'credentials-id',
                                       keyFileVariable: 'key',
                                       usernameVariable: 'username',
                                       passphraseVariable: 'passphrase')]) {
      creds = "\nUsername: ${username}\nPassphrase: ${passphrase}\n"
      sh "cat ${key}"
    }
    println creds
  }
}
