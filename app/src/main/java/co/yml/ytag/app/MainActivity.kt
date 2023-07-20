package co.yml.ytag.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.yml.ytag.app.ui.AppScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity]: Launcher Activity
 *
 * @constructor Create empty Main activity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}
