organization    := "com.tmorozov"

version         := "0.1"

scalaVersion    := "2.11.6"

scalacOptions    := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions in Test   ++= Seq("-Yrangepos")

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

libraryDependencies ++= {
    val akkaVer = "2.3.9"
    val sprayVer    = "1.3.3"
    Seq(
        "io.spray"          %% "spray-can"      % sprayVer,
        "io.spray"          %% "spray-routing"  % sprayVer,
        "io.spray"          %% "spray-testkit"  % sprayVer % "test",
        "io.spray"          %% "spray-json"     % "1.3.2",
        "com.typesafe.akka" %% "akka-actor"     % akkaVer,
        "com.typesafe.akka" %% "akka-testkit"   % akkaVer % "test",
        "org.specs2"        %% "specs2-core"    % "2.3.11" % "test"
    )
}

Revolver.settings
