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
  .dependsOn(application,domain,infrastructure)
  .aggregate(application,domain,infrastructure)
  .settings(commonSettings)
  .settings(Assets / resourceDirectory := baseDirectory.value / "public")

lazy val application = (project in file("app/application"))
  .dependsOn(domain, infrastructure)
  .settings(commonSettings)

lazy val domain = (project in file("app/domain"))
  .settings(commonSettings)

lazy val infrastructure = (project in file("app/infrastructure"))
  .dependsOn(domain)
  .settings(commonSettings)