# SECRET_TEXT - this can be get from credential.xml and it looks like {LOTS_OF_LETTER_NUMBER==}


println(hudson.util.Secret.decrypt("SECRET_TEXT"))

println(hudson.util.Secret.fromString("SECRET_TEXT").getPlainText())

println(hudson.util.Secret.fromString("PASSWORD").getEncryptedValue())
