name := "spring-akka-streams-support"
organization := "com.lightbend.akka"

version := "0.1"

scalaVersion := "2.12.3"
        
val akkaVersion = "2.5.5"
val springVersion = "5.0.0.RELEASE"

libraryDependencies += "org.springframework" % "spring-core" % springVersion
libraryDependencies += "org.springframework" % "spring-context" % springVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion
