import java.io.FileInputStream
import java.util.Properties

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        val githubProperties = Properties()
        githubProperties.load(FileInputStream(File("github.properties")))
        maven {
            url = uri("https://maven.pkg.github.com/javymarmol/compose-stepper")
            credentials {
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("USERNAME")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("TOKEN")
            }
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "compose-stepper"
include(":app")
include(":composeStepper")
