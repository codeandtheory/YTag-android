package co.yml.ytag.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.yml.ytag.feature.ytag.ui.YTagCatalog

/**
 * Application navigation component.
 */
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            YTagCatalog()
        }
    }
}
