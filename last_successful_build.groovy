def numDaysBack = 7

def cutOfDate = System.currentTimeMillis() - 1000L * 60 * 60 * 24 * numDaysBack

for (job in Jenkins.instance.getAllItems(Job.class)) {
  build = job.getLastBuild()
  if (build != null && build.getTimeInMillis() > cutOfDate) {
    println job.getFullName()
    //or do the necessary action you want for
  }
}
