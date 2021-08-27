package io.optimus.codegen

import com.sksamuel.hoplite.ConfigLoader
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import kotlin.io.path.Path

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

	@TaskAction
	fun executeTask() {
		val optimusExtension = project.extensions.getByName("optimus") as OptimusExtension
		val schemaFile = project.layout.projectDirectory.file(optimusExtension.schemaFilePath).toString()
		val config = ConfigLoader().loadConfigOrThrow<Schema>(Path(schemaFile))
	}
}