package co.yml.coreui.ui.ystepper

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import co.yml.coreui.core.ui.ystepper.StepperView
import co.yml.coreui.core.ui.ystepper.model.StepperIcon
import co.yml.coreui.core.ui.ystepper.model.StepperModifiers
import co.yml.coreui.ui.R
import org.junit.Rule
import org.junit.Test

class StepperViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun launchStepper(
        textView: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        deleteIcon: @Composable (() -> Unit)? = null,
        stepperModifiers: StepperModifiers = StepperModifiers.Builder().build(),
        visible: Boolean = true
    ) {
        composeTestRule.setContent {
            StepperView(
                textView = textView,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                deleteIcon = deleteIcon,
                stepperModifier = stepperModifiers,
                visible = visible
            )
        }
    }

    @Test
    fun stepper_view_shown() {
        launchStepper()

        composeTestRule.onNodeWithTag("stepper_view").assertIsDisplayed()
    }

    @Test
    fun default_text_view_shown() {
        launchStepper()

        composeTestRule.onNodeWithTag("text_view_default", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun custom_text_view_shown() {
        launchStepper(
            textView = { Text(text = "1") }
        )

        composeTestRule.onNodeWithTag("text_view_custom", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun text_view_text_shown(){
        val text = "1"
        val stepperModifiers = StepperModifiers.Builder().text(text).build()

        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithText(text, useUnmergedTree = true)
            .assertIsDisplayed()
    }


    @Test
    fun default_leading_icon_shown() {
        launchStepper()

        composeTestRule.onNodeWithTag("leading_icon_default", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun custom_leading_icon_shown() {
        launchStepper(
            leadingIcon = {
                IconButton(enabled = true, onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove_20px),
                        contentDescription = null
                    )
                }
            }
        )

        composeTestRule.onNodeWithTag("leading_icon_custom", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun default_trailing_icon_shown() {
        launchStepper()

        composeTestRule.onNodeWithTag("trailing_icon_default", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun custom_trailing_icon_shown() {
        launchStepper(
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_20px),
                        contentDescription = null
                    )
                }
            }
        )

        composeTestRule.onNodeWithTag("trailing_icon_custom", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun default_delete_icon_shown() {
        val stepperModifiers = StepperModifiers.Builder().showDeleteIcon(true).build()
        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithTag("delete_icon_default", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun custom_delete_icon_shown() {
        val stepperModifiers = StepperModifiers.Builder().showDeleteIcon(true).build()

        launchStepper(
            stepperModifiers = stepperModifiers,
            deleteIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_20px),
                        contentDescription = null
                    )
                }
            }
        )

        composeTestRule.onNodeWithTag("delete_icon_custom", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun leading_icon_semantics_added() {
        val semantics = "leading_icon"
        val stepperModifiers = StepperModifiers.Builder().leadingIcon(
            StepperIcon(
                icon = R.drawable.ic_remove_20px,
                iconTint = Color.Black,
                semantics = semantics
            )
        ).build()

        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithContentDescription(semantics, useUnmergedTree = true)
    }

    @Test
    fun trailing_icon_semantics_added() {
        val semantics = "trailing_icon"
        val stepperModifiers = StepperModifiers.Builder().trailingIcon(
            StepperIcon(
                icon = R.drawable.ic_add_20px,
                iconTint = Color.Black,
                semantics = semantics
            )
        ).build()

        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithContentDescription(semantics, useUnmergedTree = true)
    }

    @Test
    fun delete_icon_semantics_added() {
        val semantics = "delete_icon"
        val stepperModifiers = StepperModifiers.Builder().deleteIcon(
            StepperIcon(
                icon = R.drawable.ic_add_20px,
                iconTint = Color.Black,
                semantics = semantics
            )
        ).build()

        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithContentDescription(semantics, useUnmergedTree = true)
    }

    @Test
    fun text_view_semantics_added(){
        val semantics = "count"

        val stepperModifiers = StepperModifiers.Builder().textViewSemantics(semantics).build()
        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithContentDescription(semantics, useUnmergedTree = true)
    }

    @Test
    fun is_min_value_limit() {
        val minValue = 1
        val count = 1
        val enableLeadingIcon = count > minValue

        val stepperModifiers = StepperModifiers.Builder()
            .minValue(minValue)
            .leadingIcon(
                StepperIcon(
                    R.drawable.ic_remove_20px,
                    iconTint = Color.Black,
                    enable = enableLeadingIcon
                )
            )
            .build()

        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithTag("leading_icon_button_default", useUnmergedTree = true)
            .assertIsNotEnabled()
    }

    @Test
    fun is_max_value_limit() {
        val maxValue = 10
        val count = 10
        val enableTrailingIcon = count < maxValue

        val stepperModifiers = StepperModifiers.Builder()
            .minValue(maxValue)
            .trailingIcon(
                StepperIcon(
                    R.drawable.ic_remove_20px,
                    iconTint = Color.Black,
                    enable = enableTrailingIcon
                )
            )
            .build()

        launchStepper(stepperModifiers = stepperModifiers)

        composeTestRule.onNodeWithTag("trailing_icon_button_default", useUnmergedTree = true)
            .assertIsNotEnabled()
    }

    @Test
    fun is_stepper_ui_removed_from_ui_tree() {
        launchStepper(visible = false)

        composeTestRule.onNodeWithTag("stepper_view").assertDoesNotExist()
    }
}
