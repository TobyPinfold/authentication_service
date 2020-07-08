lazy val akkaHttpVersion = "10.2.0-M1"
lazy val akkaVersion = "2.6.5"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "dev.tobypinfold",
      scalaVersion := "2.13.1"
    )),
    name := "Akka_Authentication_Service",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.github.pureconfig" %% "pureconfig" % "0.12.3",
      "com.typesafe.slick" %% "slick" % "3.3.2",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
      "org.slf4j" % "slf4j-nop" % "1.6.4",
      "mysql" % "mysql-connector-java" % "latest.release",
      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.0.8" % Test
    )
  )
