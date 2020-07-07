name := "sttp-openapi-generator-example"

version := "0.1"

scalaVersion := "2.13.2"

val root = file(".")

lazy val generated = project
  .in(file("generated"))
  .settings(
    openApiInputSpec := "petstore.yaml",
    openApiGeneratorName := "scala-sttp",
    openApiOutputDir := "generated",
    openApiIgnoreFileOverride := s"$root/ignore-file",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core" % "2.2.0",
      "com.softwaremill.sttp.client" %% "json4s" % "2.2.0",
      "org.json4s" %% "json4s-jackson" % "3.6.8"
    )
  )

lazy val core = project
  .in(file("core"))
  .dependsOn(generated)

lazy val rootProject = project
  .in(root)
  .aggregate(generated, core)
