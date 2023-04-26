package co.yml.ytag.ui.ytag

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import co.yml.ytag.ui.ytag.model.TagViewContainerModifiers
import co.yml.ytag.ui.ytag.model.TagViewData
import co.yml.ytag.ui.ytag.model.TagViewModifiers
import org.junit.Rule
import org.junit.Test

class TagViewContainerTesting {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun launchTagViewContainer(
        tagViewContainerModifiers: TagViewContainerModifiers = TagViewContainerModifiers.Builder()
            .shape(RoundedCornerShape(10.dp))
            .width(200.dp)
            .height(120.dp)
            .build()
    ) {
        val tagViewModifiers = TagViewModifiers.Builder()
            .shape(CircleShape)
            .backgroundColor(Color.Black)
            .textColor(Color.White)
            .build()

        val tagViewData = listOf(
            TagViewData(text = "Tag 1", tagViewModifiers = tagViewModifiers),
            TagViewData(text = "Tag 2", tagViewModifiers = tagViewModifiers),
            TagViewData(text = "Tag 3", tagViewModifiers = tagViewModifiers),
            TagViewData(text = "Tag 4", tagViewModifiers = tagViewModifiers)
        )

        composeTestRule.setContent {
            TagViewContainer(
                tagViewData = tagViewData,
                tagViewContainerModifiers = tagViewContainerModifiers
            )
        }
    }

    @Test
    fun tagViewContainer_shown() {
        launchTagViewContainer()

        println(
            "tag_view_container ${composeTestRule.onNodeWithTag("tag_view_container", useUnmergedTree = true).printToString()}"
        )

        composeTestRule.onNodeWithTag("tag_view_container").assertIsDisplayed()
    }

    @Test
    fun tagViewContainer_with_modifiers_are_executed() {
        val tagViewContainerModifiers = TagViewContainerModifiers.Builder()
            .minWidth(150.dp)
            .minHeight(150.dp)
            .width(200.dp)
            .height(150.dp)
            .enableBorder(true)
            .borderWidth(1.dp)
            .borderColor(Color.Red)
            .backgroundColor(Color.Gray)
            .shape(CircleShape)
            .containerPaddingValues(PaddingValues(8.dp))
            .tagSpacingHorizontal(8.dp)
            .tagSpacingVertical(8.dp)
            .moreTagConfiguration(TagViewData(text = "more"))
            .build()

        launchTagViewContainer(tagViewContainerModifiers)

        composeTestRule.onNodeWithTag("tag_view_container")
            .assertIsDisplayed()
    }

    @Test
    fun tagViewContainer_tags_shown() {
        launchTagViewContainer()

        composeTestRule.onNodeWithText("Tag 1").assertIsDisplayed()
    }

    @Test
    fun tagViewContainer_with_less_space_more_tag_shown() {
        val tagViewContainerModifiers = TagViewContainerModifiers.Builder()
            .width(150.dp)
            .height(50.dp)
            .moreTagConfiguration(
                configuration = TagViewData(overFlowText = { "more" })
            )
            .build()

        launchTagViewContainer(tagViewContainerModifiers)

        composeTestRule.onNodeWithText("more").assertIsDisplayed()
    }
}
