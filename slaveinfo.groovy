jenkins.model.Jenkins.instance.computers.each { c ->
  println c.node.labelString
}
