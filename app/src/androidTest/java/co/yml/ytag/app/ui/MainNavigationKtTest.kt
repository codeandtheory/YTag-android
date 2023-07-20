package co.yml.ytag.app.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import co.yml.ytag.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

/**
 * Main navigation kt test
 *
 * @constructor Creates empty Main navigation kt test
 */
@HiltAndroidTest
class MainNavigationKtTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Test navigation launched
     *
     */
    @Test
    fun testNavigationLaunched() {
        composeTestRule.onNodeWithTag("y_tag").assertIsDisplayed()
    }
}
