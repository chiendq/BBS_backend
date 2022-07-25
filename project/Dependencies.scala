import sbt._
import play.sbt.PlayImport._

object Dependencies {
  lazy val defaultDependencies: Seq[ModuleID] = Seq(jdbc, guice, specs2 % Test,ws
//    evolutions
  )

  lazy val addedDependencies: Seq[ModuleID] = Seq(
    "com.auth0" % "java-jwt" % "3.3.0",
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
    "mysql" % "mysql-connector-java" % "8.0.29",
    "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
    "org.scalikejdbc" %% "scalikejdbc-config" % "3.5.0",
    "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5",
    "org.skinny-framework" %% "skinny-framework" % "3.1.0",
    // hashing libraries
    "com.github.t3hnar" %% "scala-bcrypt" % "4.3.0")
}
