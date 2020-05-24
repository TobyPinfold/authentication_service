lazy val akkaHttpVersion = "maven(com.typesafe.akka, akka-http-core_2.13, stable)"
lazy val akkaVersion    = "maven(com.typesafe.akka, akka-actor-typed_2.13, stable)"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "dev.tobypinfold",
      scalaVersion    := "2.13.1"
    )),
    name := "Akka_Authentication_Service",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
      "ch.qos.logback"    % "logback-classic"           % "1.2.3",

      "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"                % "3.0.8"         % Test
    )
  )
