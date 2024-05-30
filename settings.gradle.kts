pluginManagement {
    includeBuild("build-logic")
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
        google()
        mavenCentral()
    }
}

rootProject.name = "wms"
include(":app")
include(":core")
include(":feature")
// put all shared modules here
// e.g.: include(":core:model)
// e.g.: include(":feature:search)
include(":feature:authentication")
include(":feature:event_list")
include(":feature:event_admin")
include(":feature:event_user")
