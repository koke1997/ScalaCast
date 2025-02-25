name := "ScalaCast"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

// Add JDK 21 settings
javacOptions ++= Seq("-source", "17", "-target", "17")

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "6.0.0" % Test
)