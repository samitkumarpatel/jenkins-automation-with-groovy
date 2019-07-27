Hudson.instance.items.each { job ->
  if ( job.getTrigger(  hudson.triggers.SCMTrigger ) != null ) {
    println "will disable job ${job.name}"
    job.disable()
  }
}
