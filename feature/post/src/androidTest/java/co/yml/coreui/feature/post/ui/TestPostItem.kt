package co.yml.coreui.feature.post.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme

class TestPostItem {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testShowLocalPostScreen() {
        // Start the app
        composeTestRule.setContent {
            CoreUICatalogTheme {
                PostListItem(Post(0, 0, "Post Title", "postBody"), Modifier) {
                }
            }
        }

        composeTestRule.onNodeWithText("Post Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("postBody").assertIsDisplayed()
    }
}
