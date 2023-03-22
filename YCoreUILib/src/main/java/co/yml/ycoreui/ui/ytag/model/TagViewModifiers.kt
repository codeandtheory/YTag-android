package co.yml.ycoreui.ui.ytag.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TagViewModifiers(
    val text: String,
    val textColor: Color,
    val fontSize: TextUnit,
    val style: TextStyle,
    val enableBorder: Boolean,
    val borderWidth: Dp,
    val borderColor: Color,
    val backgroundColor: Color,
    val startPadding: Dp,
    val topPadding: Dp,
    val bottomPadding: Dp,
    val endPadding: Dp,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val shape: Shape
) {
    class Builder {
        private var text: String = ""
        private var textColor: Color = Color.Black
        private var fontSize: TextUnit = 12.sp
        private var style = TextStyle()
        private var enableBorder: Boolean = false
        private var borderWidth: Dp = 1.dp
        private var borderColor: Color = Color.Black
        private var backgroundColor: Color = Color.White
        private var startPadding: Dp = 8.dp
        private var topPadding: Dp = 4.dp
        private var bottomPadding: Dp = 4.dp
        private var endPadding: Dp = 8.dp
        private var leadingIcon: @Composable (() -> Unit)? = null
        private var trailingIcon: @Composable (() -> Unit)? = null
        private var shape: Shape = RectangleShape

        fun text(text: String) = apply { this.text = text }

        fun textColor(textColor: Color) = apply { this.textColor = textColor }

        fun fontSize(fontSize: TextUnit) = apply { this.fontSize = fontSize }

        fun style(style: TextStyle) = apply { this.style = style }

        fun enableBorder(enableBorder: Boolean) = apply { this.enableBorder = enableBorder }

        fun borderWidth(borderWidth: Dp) = apply { this.borderWidth = borderWidth }

        fun borderColor(borderColor: Color) = apply { this.borderColor = borderColor }

        fun backgroundColor(backgroundColor: Color) =
            apply { this.backgroundColor = backgroundColor }

        fun startPadding(startPadding: Dp) = apply { this.startPadding = startPadding }

        fun topPadding(topPadding: Dp) = apply { this.topPadding = topPadding }

        fun bottomPadding(bottomPadding: Dp) = apply { this.bottomPadding = bottomPadding }

        fun endPadding(endPadding: Dp) = apply { this.endPadding = endPadding }

        fun leadingIcon(leadingIcon: @Composable (() -> Unit)?) = apply { this.leadingIcon = leadingIcon }

        fun trailingIcon(trailingIcon: @Composable (() -> Unit)?) = apply { this.trailingIcon = trailingIcon }

        fun shape(shape: Shape) = apply { this.shape =shape }

        fun build() = TagViewModifiers(
            text,
            textColor,
            fontSize,
            style,
            enableBorder,
            borderWidth,
            borderColor,
            backgroundColor,
            startPadding,
            topPadding,
            bottomPadding,
            endPadding,
            leadingIcon,
            trailingIcon,
            shape
        )
    }
}
