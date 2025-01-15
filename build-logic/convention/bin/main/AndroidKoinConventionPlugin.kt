import com.android.build.api.dsl.ApplicationExtension
import com.landmuc.wms.configureKoin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureKoin()
        }

    }
}