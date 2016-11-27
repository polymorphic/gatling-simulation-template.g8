import sbt._
import sbt.Keys._

lazy val projectResolvers = Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

lazy val gatlingLibs = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "$gatlingVersion$" % "test"
    , "io.gatling" % "gatling-test-framework" % "$gatlingVersion$" % "test"
  )

lazy val libs = {
  Seq(
    "com.typesafe.akka" % "akka-actor_2.11" % "2.4.14"
    , "com.typesafe.akka" % "akka-http_2.11" % "10.0.0"
    , "com.typesafe.akka" % "akka-http-core_2.11" % "10.0.0"
    , "com.typesafe.akka" % "akka-http-spray-json_2.11" % "10.0.0"
  )
}

lazy val sharedSettings = Seq(
  version := "1.0",
  organization := "$package$",
  scalaVersion := "2.11.8",
  resolvers := projectResolvers,
  libraryDependencies := libs
)

lazy val domain = (project in file("domain")).
  settings(sharedSettings: _*).
  settings(
    name := "domain"
  )

lazy val svc = (project in file("svc")).
  settings(sharedSettings: _*).
  settings(
    name := "service"
  ).dependsOn(domain)

lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .settings(sharedSettings: _*)
  .settings(
    name := "simulations"
    , libraryDependencies ++= gatlingLibs
  ).dependsOn(svc)

