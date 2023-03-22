package co.yml.coreui.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import co.yml.coreui.MainActivity

/**
 * Main navigation kt test
 *
 * @constructor Creates empty Main navigation kt test
 */
class MainNavigationKtTest {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Test navigation launched
     *
     */
    @Test
    fun testNavigationLaunched() {
        composeTestRule.onNodeWithTag("show_new_post_button").assertIsDisplayed()
    }
}
