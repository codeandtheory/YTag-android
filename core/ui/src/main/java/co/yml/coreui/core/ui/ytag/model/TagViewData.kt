package co.yml.coreui.core.ui.ytag.model

import androidx.compose.runtime.Composable

/**
 * [TagViewData] Used for holding the TagView data
 *
 * @param text Tag view text to be displayed
 * @param leadingIcon the optional leading icon to be displayed at the beginning of the TagView
 * @param trailingIcon the optional leading icon to be displayed at the end of the TagView
 * @param enabled controls the enabled state of the TagView
 * @param tagViewModifiers collection of modifier elements that decorate or add behavior to TagView elements
 * @param showOverFlow show or hide over flow text
 * @param overFlowText to be displayed for over flow tag [use overFlowText instead of [text] for over flow tag ]
 */
data class TagViewData(
    val text: String = "",
    val tagViewModifiers: TagViewModifiers = TagViewModifiers.Builder().build(),
    val leadingIcon: @Composable ((tagViewData: TagViewData) -> Unit)? = null,
    val trailingIcon: @Composable ((tagViewData: TagViewData) -> Unit)? = null,
    val enabled: Boolean = true,
    val showOverFlow: Boolean = true,
    val overFlowText: (Int) -> String = { _ -> "" }
)

data class AlphaAnimation(var enabled: Boolean = true, val durationMillis: Int = 650)
