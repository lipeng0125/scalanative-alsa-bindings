ThisBuild / scalaVersion := "3.3.3"

// resolvers += "central" at "https://maven.aliyun.com/repository/public"

enablePlugins(ScalaNativePlugin)

// set to Debug for compilation details (Info is default)
logLevel := Level.Debug

// import to add Scala Native options
import scala.scalanative.build.*

// defaults set with common options shown
ThisBuild / nativeConfig ~= {
  _.withLTO(LTO.none) // thin
    .withMode(Mode.releaseFull) // releaseFast
    .withGC(GC.commix) // commix
    .withCompileOptions(Seq("-w"))
    .withBuildTarget(BuildTarget.libraryDynamic)
    .withOptimize(true)
}

lazy val gweb = (project in file("."))
  .settings(
    organization := "gweb",
    name := "scalanative-alsa-bindings",
    version := "0.0.1-alpha-1",
    libraryDependencies ++= Seq(
    )
  )
