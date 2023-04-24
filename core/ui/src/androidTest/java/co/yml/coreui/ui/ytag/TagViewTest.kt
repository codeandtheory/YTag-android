package co.yml.coreui.ui.ytag

import android.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.coreui.core.ui.ytag.TagView
import co.yml.coreui.core.ui.ytag.model.TagViewData
import co.yml.coreui.core.ui.ytag.model.TagViewModifiers
import org.junit.Rule
import org.junit.Test

class TagViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun launchYTag(
        text: String,
        leadingIcon: @Composable ((TagViewData) -> Unit)? = null,
        trailingIcon: @Composable ((TagViewData) -> Unit)? = null,
        tagViewModifiers: TagViewModifiers = TagViewModifiers.Builder().build(),
        enabled: Boolean = true
    ) {
        composeTestRule.setContent {
            TagView(
                text = text,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                tagViewModifiers = tagViewModifiers,
                enabled = enabled
            )
        }
    }

    @Test
    fun tagView_shown() {
        launchYTag(text = "YTag")
        composeTestRule.onNodeWithTag("tag_view", useUnmergedTree = true).printToString()

        composeTestRule.onNodeWithTag("tag_view").assertIsDisplayed()
    }

    @Test
    fun tagView_text_shown() {
        launchYTag(text = "YTag")

        composeTestRule.onNodeWithText("YTag").assertIsDisplayed()
    }

    @Test
    fun tagView_leading_icon_shown() {
        launchYTag(text = "YTag", leadingIcon = {
            IconButton(onClick = {}, modifier = Modifier.testTag("leading_icon")) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_mylocation),
                    contentDescription = null
                )
            }
        })

        composeTestRule.onNodeWithTag("leading_icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun tagView_trailing_icon_shown() {
        launchYTag(text = "YTag", trailingIcon = {
            IconButton(onClick = {}, modifier = Modifier.testTag("trailing_icon")) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_mylocation),
                    contentDescription = null
                )
            }
        })

        composeTestRule.onNodeWithTag("trailing_icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun tagView_leading_and_trailing_icon_shown() {
        launchYTag(text = "YTag", leadingIcon = {
            IconButton(onClick = {}, modifier = Modifier.testTag("leading_icon")) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_mylocation),
                    contentDescription = null
                )
            }
        }, trailingIcon = {
                IconButton(onClick = {}, modifier = Modifier.testTag("trailing_icon")) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu_mylocation),
                        contentDescription = null
                    )
                }
            })

        composeTestRule.onNodeWithTag("leading_icon", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("trailing_icon", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun tag_with_modifiers_are_executed() {
        val tagViewModifiers = TagViewModifiers.Builder()
            .minWidth(32.dp)
            .minHeight(100.dp)
            .width(50.dp)
            .height(50.dp)
            .textColor(Color.Black)
            .fontSize(14.sp)
            .fontFamily(FontFamily.SansSerif)
            .fontStyle(FontStyle.Normal)
            .fontWeight(FontWeight.Normal)
            .letterSpacing(0.05.sp)
            .textDecoration(TextDecoration.None)
            .textAlign(TextAlign.Center)
            .lineHeight(24.sp)
            .overFlow(TextOverflow.Ellipsis)
            .softWrap(true)
            .maxLines(1)
            .onTextLayout { }
            .style(TextStyle())
            .enableBorder(true)
            .borderColor(Color.Red)
            .borderWidth(1.dp)
            .backgroundColor(Color.White)
            .textPadding(PaddingValues(4.dp))
            .shape(CircleShape)
            .tonalElevation(2.dp)
            .shadowElevation(2.dp)
            .containerPaddingValues(PaddingValues(4.dp))
            .onCLick { }
            .borderWidth(1.dp)
            .build()

        launchYTag(text = "YTag", tagViewModifiers = tagViewModifiers)

        composeTestRule.onNodeWithText("YTag")
            .assertIsDisplayed()
    }
}
