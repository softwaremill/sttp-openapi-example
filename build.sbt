name := "sttp-openapi-generator-example"

version := "0.1"

scalaVersion := "2.13.2"

lazy val petstoreApi: Project = project
  .in(file("petstore-api"))
  .settings(
    openApiInputSpec := s"${baseDirectory.value.getPath}/petstore.yaml",
    openApiGeneratorName := "scala-sttp",
    //We can't use src_managed because there is no option to tell openapi-generator to generate files into different folder than src
    //see https://github.com/OpenAPITools/openapi-generator/issues/6685 for more details
    openApiOutputDir := baseDirectory.value.name,
    //Below setting is needed to configure generator not to generate other files besides src/main/scala
    openApiIgnoreFileOverride := s"${baseDirectory.in(ThisBuild).value.getPath}/openapi-ignore-file",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client" %% "core" % "2.2.0",
      "com.softwaremill.sttp.client" %% "json4s" % "2.2.0",
      "org.json4s" %% "json4s-jackson" % "3.6.8"
    ),
    //We can't use sourceGenerators because this requires all files to compile and openapi-generator generates
    //some additional metadata files which breaks compilation.
    //see https://github.com/OpenAPITools/openapi-generator/issues/6685 for more details
    (compile in Compile) := ((compile in Compile) dependsOn openApiGenerate).value,
    //As we don't generate files into src_managed we have to do cleaning by our own
    cleanFiles += baseDirectory.value / "src"
  )

lazy val core = project
  .in(file("core"))
  .dependsOn(petstoreApi)

lazy val rootProject = project
  .in(file("."))
  .aggregate(petstoreApi, core)
