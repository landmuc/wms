import com.android.build.gradle.LibraryExtension
import com.landmuc.wms.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
       with(target) {
           apply(plugin = "com.android.library")
           apply(plugin = "org.jetbrains.kotlin.plugin.compose") // compose compiler plugin

           val extension = extensions.getByType<LibraryExtension>()
           configureAndroidCompose(extension)
       }
    }
}