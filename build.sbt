name := "ReclamationCenter"

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.bintrayRepo("hseeberger", "maven"))

libraryDependencies ++= {
  val AkkaVersion = "2.3.9"
  val AkkaHttpVersion = "2.0.1"
  val Json4sVersion = "3.2.11"
  Seq(
    "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % AkkaHttpVersion,
    "org.json4s" %% "json4s-native" % Json4sVersion,
    "org.json4s" %% "json4s-ext" % Json4sVersion,
    "de.heikoseeberger" %% "akka-http-json4s" % "1.4.2",
    "ch.qos.logback" % "logback-classic" % "1.1.2"
  )
}
    