rootProject.name = "optimus"
include("optimus-core")
include("optimus-sql")
include("optimus-mongo")
include("optimus-plugin")
include("optimus-test-app")

pluginManagement {
	repositories {
		mavenLocal()
		gradlePluginPortal()
	}
}