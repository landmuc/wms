import com.landmuc.wms.configureSupabase
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidSupabaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
       with(target) {
           configureSupabase()
       }
    }
}