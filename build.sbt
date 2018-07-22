name := "rws-scala-sdk"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.0.0-M2",
  "com.typesafe.play" %% "play-ws-standalone-json" % "2.0.0-M2",
  "org.specs2" %% "specs2-core" % "4.3.2" % Test,
  "org.specs2" %% "specs2-mock" % "4.3.2" % Test
)

scalacOptions in Test ++= Seq("-Yrangepos")