package io.optimus.codegen

import com.typesafe.config.ConfigFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
val hocon = Hocon {}

class OptimusPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		project.extensions.create("optimus", OptimusExtension::class.java)
		project.tasks.create("generateOptimusCode", GenerateOptimusCodeTask::class.java)
	}
}

sealed class OptimusFeature

object OptimusMongo : OptimusFeature()
object OptimusSql : OptimusFeature()

open class OptimusExtension {
	var features: List<OptimusFeature> = emptyList()
	var schemaFilePath: String = ""
}

open class GenerateOptimusCodeTask : DefaultTask() {

	@OptIn(ExperimentalSerializationApi::class)
	@TaskAction
	fun executeTask() {
		val optimusExtension = project.extensions.getByName("optimus") as OptimusExtension
		val schemaFilePath = project.layout.projectDirectory.file(optimusExtension.schemaFilePath).toString()
		val schemaConfig = ConfigFactory.parseFile(File(schemaFilePath))
		val schema = hocon.decodeFromConfig<Schema>(schemaConfig)
		println(schema)
	}
}