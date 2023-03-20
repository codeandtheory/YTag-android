package co.yml.coreui.feature.post.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme

class TestNewLocalPostScreen {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testShowLocalPostScreen() {
        // Start the app
        composeTestRule.setContent {
            CoreUICatalogTheme {
                ShowCreatePostOption(Modifier) {
                }
            }
        }

        composeTestRule.onNodeWithTag("show_new_post_button").assertIsDisplayed()
        composeTestRule.onNodeWithTag("show_new_post_button").performClick()
        composeTestRule.onNodeWithTag("title_tag").assertIsDisplayed()
    }
}
