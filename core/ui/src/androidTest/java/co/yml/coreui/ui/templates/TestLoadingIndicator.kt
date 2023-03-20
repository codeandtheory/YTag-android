package co.yml.coreui.ui.templates

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import co.yml.coreui.core.ui.templates.AppLoadingIndicator
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme

class TestLoadingIndicator {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingIndicator() {
        // Start the app
        composeTestRule.setContent {
            CoreUICatalogTheme {
                AppLoadingIndicator()
            }
        }

        composeTestRule.onNodeWithTag("loadingIndicator").assertIsDisplayed()
    }
}
