name := "sttp-openapi-generator-example"

version := "0.1"

scalaVersion := "2.13.2"

val root = file(".")

val petstoreApiDir = "petstore-api"
lazy val petstoreApi = project
  .in(file(petstoreApiDir))
  .settings(
    openApiInputSpec := s"${baseDirectory.value.getPath}/petstore.yaml",
    openApiGeneratorName := "scala-sttp",
    openApiOutputDir := baseDirectory.value.name,
    openApiIgnoreFileOverride := s"$root/openapi-ignore-file",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core" % "2.2.0",
      "com.softwaremill.sttp.client" %% "json4s" % "2.2.0",
      "org.json4s" %% "json4s-jackson" % "3.6.8"
    ),
    (compile in Compile) := ((compile in Compile) dependsOn openApiGenerate).value,
    cleanFiles += baseDirectory.value / "src"
  )

lazy val core = project
  .in(file("core"))
  .dependsOn(petstoreApi)

lazy val rootProject = project
  .in(root)
  .aggregate(petstoreApi, core)
