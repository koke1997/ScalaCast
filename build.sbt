name := """ScalaCast"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

val akkaVersion = "2.7.0"

libraryDependencies ++= Seq(
  guice,
  // Play Framework dependencies
  "com.typesafe.play" %% "play" % "2.9.0",
  "com.typesafe.play" %% "play-json" % "2.9.0",
  
  // Updated Akka dependencies with consistent versions
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.2.10",
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  
  // Other dependencies
  "javax.inject" % "javax.inject" % "1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "com.pauldijou" %% "jwt-play" % "5.0.0",
  "javax.websocket" % "javax.websocket-api" % "1.1"
)

resolvers ++= Seq(
  "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Maven Central" at "https://repo1.maven.org/maven2/",
  "JCenter" at "https://jcenter.bintray.com/"
)