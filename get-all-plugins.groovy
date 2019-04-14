def plugins = Jenkins.instance.pluginManager.plugins
def mlist=[]
plugins.each {
  mlist.add(new String("\""+it.getShortName()+"\""))
}
println(mlist)