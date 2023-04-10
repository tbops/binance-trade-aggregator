ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val contracts = project
  .in(file("contracts"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "contracts",
    Compile / mainClass := Some("Contracts"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.13" % Test,
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.typelevel" %% "cats-effect" % "3.4.2",
      "io.github.paoloboni" %% "binance-scala-client" % "1.5.9",
      "com.github.fd4s" %% "fs2-kafka" % "3.0.0-M8",
      "io.circe" %% "circe-core" % "0.14.3",
      "io.circe" %% "circe-generic" % "0.14.3",
      "io.circe" %% "circe-parser" % "0.14.3",
      "ch.qos.logback" % "logback-classic" % "1.4.5",
      "co.fs2" %% "fs2-core" % "3.4.0",
      "co.fs2" %% "fs2-io" % "3.4.0",
      "co.fs2" %% "fs2-reactive-streams" % "3.4.0",
    )
  )

lazy val producer = project
  .in(file("producer"))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "producer",
    Compile / mainClass := Some("Producer"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.13" % Test,
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.typelevel" %% "cats-effect" % "3.4.2",
      "io.github.paoloboni" %% "binance-scala-client" % "1.5.9",
      "com.github.fd4s" %% "fs2-kafka" % "3.0.0-M8",
      "io.circe" %% "circe-core" % "0.14.3",
      "io.circe" %% "circe-generic" % "0.14.3",
      "io.circe" %% "circe-parser" % "0.14.3",
      "ch.qos.logback" % "logback-classic" % "1.4.5",
      "co.fs2" %% "fs2-core" % "3.4.0",
      "co.fs2" %% "fs2-io" % "3.4.0",
      "co.fs2" %% "fs2-reactive-streams" % "3.4.0",
    )
  )
  .dependsOn(contracts)
  .aggregate(contracts)

lazy val root = (project in file("."))
  .settings(
    name := "binance-trade-aggregator",
  )
  .aggregate(
    contracts,
    producer
  )
