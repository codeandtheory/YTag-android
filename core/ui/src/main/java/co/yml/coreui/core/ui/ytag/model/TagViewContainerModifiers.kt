package co.yml.coreui.core.ui.ytag.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//todo sree_ is min width and min height required?
/**
 * [TagViewContainerModifiers] Immutable collection of modifier elements that decorate or add behavior to TagView container.
 * @param minWidth define a default min width of TagViewContainer
 * @param minHeight define a default min height of TagViewContainer
 * @param width define width of TagViewContainer
 * @param height define height of TagViewContainer
 * @param enableBorder enable border for TagViewContainer
 * @param borderWidth define borderWidth of the TagViewContainer
 * @param borderColor define borderColor of the TagViewContainer
 * @param backgroundColor define backgroundColor of the TagViewContainer
 * @param shape defines the shape of the TagViewContainer
 * @param containerPaddingValues define padding for TagViewContainer
 * @param tagSpacingHorizontal horizontal padding between tag views
 * @param tagSpacingVertical vertical padding between tag views
 *
 */
data class TagViewContainerModifiers(
    val minWidth: Dp,
    val minHeight: Dp,
    val width: Dp?,
    val height: Dp,
    val enableBorder: Boolean,
    val borderWidth: Dp,
    val borderColor: Color,
    val backgroundColor: Color,
    val shape: Shape,
    val containerPaddingValues: PaddingValues,
    val tagSpacingHorizontal: Dp,
    val tagSpacingVertical: Dp
) {
    //todo sree_ check min and max default size
    class Builder {
        private var minWidth: Dp = 150.dp
        private var minHeight: Dp = 150.dp
        private var width: Dp? = null
        private var height: Dp = minHeight
        private var enableBorder: Boolean = false
        private var borderWidth: Dp = 1.dp
        private var borderColor: Color = Color.Black
        private var backgroundColor: Color = Color.White
        private var shape: Shape = RectangleShape
        private var containerPaddingValues: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
        private var tagSpacingHorizontal: Dp = 8.dp
        private var tagSpacingVertical: Dp = 8.dp


        fun minWidth(minWidth: Dp) = apply { this.minWidth = minWidth }

        fun minHeight(minHeight: Dp) = apply { this.minHeight = minHeight }

        fun width(width: Dp) = apply { this.width = width }

        fun height(height: Dp) = apply { this.height = height }

        fun enableBorder(enableBorder: Boolean) = apply { this.enableBorder = enableBorder }

        fun borderWidth(borderWidth: Dp) = apply { this.borderWidth = borderWidth }

        fun borderColor(borderColor: Color) = apply { this.borderColor = borderColor }

        fun backgroundColor(backgroundColor: Color) =
            apply { this.backgroundColor = backgroundColor }

        fun shape(shape: Shape) = apply { this.shape = shape }

        fun containerPaddingValues(paddingValues: PaddingValues) =
            apply { this.containerPaddingValues = paddingValues }

        fun tagSpacingHorizontal(space: Dp) = apply { this.tagSpacingHorizontal = space }

        fun tagSpacingVertical(space: Dp) = apply { this.tagSpacingVertical = space }

        fun build() = TagViewContainerModifiers(
            minWidth,
            minHeight,
            width,
            height,
            enableBorder,
            borderWidth,
            borderColor,
            backgroundColor,
            shape,
            containerPaddingValues,
            tagSpacingHorizontal,
            tagSpacingVertical
        )
    }
}
