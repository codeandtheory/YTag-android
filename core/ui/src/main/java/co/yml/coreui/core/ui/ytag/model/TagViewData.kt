package co.yml.coreui.core.ui.ytag.model

import androidx.compose.runtime.Composable

/**
 * [TagViewData] Used for holding the TagView data
 *
 * @param text text the text to be displayed
 * @param leadingIcon the optional leading icon to be displayed at the beginning of the TagView
 * @param trailingIcon the optional leading icon to be displayed at the end of the TagView
 * @param enabled controls the enabled state of the TagView
 * @param tagViewModifiers collection of modifier elements that decorate or add behavior to TagView elements
 */
data class TagViewData(
    val text: String,
    val tagViewModifiers: TagViewModifiers = TagViewModifiers.Builder().build(),
    val leadingIcon: @Composable ((enable: Boolean) -> Unit)? = null,
    val trailingIcon: @Composable ((enable: Boolean) -> Unit)? = null,
    val enabled: Boolean = true,
    val overFlowText: (Int) -> String =  { _ -> "" }
)
