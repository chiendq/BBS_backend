ThisBuild / scalaVersion := "2.13.8"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """chien_dq_bbs_backend""",
    libraryDependencies ++= Seq(
      guice,
      // Evolution
      evolutions,

      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "mysql" % "mysql-connector-java" % "8.0.29",
      jdbc,
      "org.scalikejdbc" %% "scalikejdbc" % "3.5.0",
      "org.scalikejdbc" %% "scalikejdbc-config" % "3.5.0",
      "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5",

      "org.skinny-framework" %% "skinny-framework" % "3.1.0",

      specs2 % Test
    )
  )

