package co.yml.coreui.ui.templates

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import co.yml.coreui.core.ui.templates.StickyErrorMessageCard
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme

class TestStickyErrorMessage {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testStickyErrorMessage() {
        // Start the app
        composeTestRule.setContent {
            CoreUICatalogTheme {
                StickyErrorMessageCard("error Message")
            }
        }

        composeTestRule.onNodeWithText("error Message").assertIsDisplayed()
    }
}
