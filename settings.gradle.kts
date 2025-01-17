// Workaround for error while project rebuilding from https://issuetracker.google.com/issues/328871352?pli=1
// error: Unable to make progress running work. There are items queued for execution but none of them can be started
//gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:clean"))
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

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
include(":feature:event_details")
include(":core:network")
include(":core:data")
include(":core:domain")
include(":feature:search")
include(":feature:create_event")
include(":feature:step_details")
