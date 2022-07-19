import sbt._
import play.sbt.PlayImport._

object Dependencies {
  lazy val defaultDependencies = Seq(jdbc, guice, specs2 % Test,
//    evolutions
  )

  lazy val addedDependencies = Seq(
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
    "mysql" % "mysql-connector-java" % "8.0.29",
    "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
    "org.scalikejdbc" %% "scalikejdbc-config" % "3.5.0",
    "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5",
    "org.skinny-framework" %% "skinny-framework" % "3.1.0")
}
