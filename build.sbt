import Dependencies.{addedDependencies, defaultDependencies}

ThisBuild / version := "1.0-SNAPSHOT"

name := """chien_dq_bbs_backend"""

lazy val commonSettings = Seq(
  scalaVersion := "2.13.8",
  libraryDependencies ++= defaultDependencies,
  libraryDependencies ++= addedDependencies,
  Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala",
  Test / scalaSource := baseDirectory.value / "src" / "test" / "scala",
  Compile / resourceDirectory := baseDirectory.value / "src" / "main" / "resources",
  Test / resourceDirectory := baseDirectory.value / "src" / "test" / "resources"
)


lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .dependsOn(application,domain,port,utility)
  .aggregate(application,domain,port,utility)
  .settings(commonSettings)

lazy val application = (project in file("app/application"))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(commonSettings)

lazy val domain = (project in file("app/domain"))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(commonSettings)

lazy val port = (project in file("app/port"))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(commonSettings)

lazy val utility = (project in file("app/utility"))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(commonSettings)

