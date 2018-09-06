name := "rws-scala-sdk"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "requests" % "0.1.4",
  "com.typesafe.play" %% "play-json-joda" % "2.6.9",
  "org.joda" % "joda-convert" % "2.1.1",
  "org.specs2" %% "specs2-core" % "4.3.2" % Test,
  "org.specs2" %% "specs2-mock" % "4.3.2" % Test
)

scalacOptions in Test ++= Seq("-Yrangepos")
