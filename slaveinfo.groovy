def slaveInfov1() {
    jenkins.model.Jenkins.instance.computers.each { c ->
        println "${c.node.labelString} Online status : ${c.node.toComputer().online} "
        /*
        node(c.node.labelString) {
            stage("whoami-${c.node.labelString}") {
                timeout(time: 3, unit: 'MINUTES') {
                    sh 'whoami'
                }
            }
        }*/
    }    
}

slaveInfov1()

def slaveInformation() {
    for (aSlave in hudson.model.Hudson.instance.slaves) {
      println('====================');
      println('Name: ' + aSlave.name);
      println('getLabelString: ' + aSlave.getLabelString());
      println('getNumExectutors: ' + aSlave.getNumExecutors());
      println('getRemoteFS: ' + aSlave.getRemoteFS());
      println('getMode: ' + aSlave.getMode());
      println('getRootPath: ' + aSlave.getRootPath());
      println('getDescriptor: ' + aSlave.getDescriptor());
      println('getComputer: ' + aSlave.getComputer());
      println('\tcomputer.isAcceptingTasks: ' + aSlave.getComputer().isAcceptingTasks());
      println('\tcomputer.isLaunchSupported: ' + aSlave.getComputer().isLaunchSupported());
      println('\tcomputer.getConnectTime: ' + aSlave.getComputer().getConnectTime());
      println('\tcomputer.getDemandStartMilliseconds: ' + aSlave.getComputer().getDemandStartMilliseconds());
      println('\tcomputer.isOffline: ' + aSlave.getComputer().isOffline());
      println('\tcomputer.countBusy: ' + aSlave.getComputer().countBusy());
      //if (aSlave.name == 'NAME OF NODE TO DELETE') {
      //  println('Shutting down node!!!!');
      //  aSlave.getComputer().setTemporarilyOffline(true,null);
      //  aSlave.getComputer().doDoDelete();
      //}
      println('\tcomputer.getLog: ' + aSlave.getComputer().getLog());
      println('\tcomputer.getBuilds: ' + aSlave.getComputer().getBuilds());
    }   
}
    
slaveInformation()
