import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "todolist"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      publishTo <<= version { (v: String) =>
        val nexus = "http://ec2-174-129-75-167.compute-1.amazonaws.com:8080/nexus/"
        if (v.trim.endsWith("SNAPSHOT")) 
          Some("snapshots" at nexus + "content/repositories/snapshots") 
        else
          Some("releases"  at nexus + "content/repositories/releases")
      },
      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
    )

}
