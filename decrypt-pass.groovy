# {ENCRYPTED_PASSPHRASE_OR_PASSWORD} - this can be get from credential.xml
println( hudson.util.Secret.decrypt("${ENCRYPTED_PASSPHRASE_OR_PASSWORD}") )
