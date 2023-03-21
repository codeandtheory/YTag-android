package co.yml.coreui.ui.ytag.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val tagShapeSize: Dp,
    val backgroundColor: Color,
    val tagShape: TagShape,
    val startPadding: Dp,
    val topPadding: Dp,
    val bottomPadding: Dp,
    val endPadding: Dp,
    val leadingIcon: Int ?=null,
    val trailingIcon: Int ?=null
) {
    class Builder {
        private var text: String = ""
        private var textColor: Color = Color.Black
        private var fontSize: TextUnit = 12.sp
        private var style = TextStyle()
        private var enableBorder: Boolean = false
        private var borderWidth: Dp = 1.dp
        private var borderColor: Color = Color.Black
        private var tagShapeSize: Dp = 8.dp
        private var backgroundColor: Color = Color.White
        private var tagShape: TagShape = TagShape.ROUNDED_RECTANGLE
        private var startPadding: Dp = 8.dp
        private var topPadding: Dp = 4.dp
        private var bottomPadding: Dp = 4.dp
        private var endPadding: Dp = 8.dp
        private var leadingIcon: Int? = null
        private var trailingIcon: Int ?=null

        fun text(text: String) = apply { this.text = text }

        fun textColor(textColor: Color) = apply { this.textColor = textColor }

        fun fontSize(fontSize: TextUnit) = apply { this.fontSize = fontSize }

        fun style(style: TextStyle) = apply { this.style = style }

        fun enableBorder(enableBorder: Boolean) = apply { this.enableBorder = enableBorder }

        fun borderWidth(borderWidth: Dp) = apply { this.borderWidth = borderWidth }

        fun borderColor(borderColor: Color) = apply { this.borderColor = borderColor }

        fun tagShapeSize(tagShapeSize: Dp) = apply { this.tagShapeSize = tagShapeSize }

        fun backgroundColor(backgroundColor: Color) =
            apply { this.backgroundColor = backgroundColor }

        fun tagShape(tagShape: TagShape) = apply { this.tagShape = tagShape }

        fun startPadding(startPadding: Dp) = apply { this.startPadding = startPadding }

        fun topPadding(topPadding: Dp) = apply { this.topPadding = topPadding }

        fun bottomPadding(bottomPadding: Dp) = apply { this.bottomPadding = bottomPadding }

        fun endPadding(endPadding: Dp) = apply { this.endPadding = endPadding }

        fun leadingIcon(icon: Int) = apply { this.leadingIcon = icon }

        fun trailingIcon(icon: Int) = apply { this.trailingIcon = icon }

        fun build() = TagViewModifiers(
            text,
            textColor,
            fontSize,
            style,
            enableBorder,
            borderWidth,
            borderColor,
            tagShapeSize,
            backgroundColor,
            tagShape,
            startPadding,
            topPadding,
            bottomPadding,
            endPadding,
            leadingIcon,
            trailingIcon
        )
    }


}

enum class TagShape {
    RECTANGLE,
    ROUNDED_RECTANGLE,
    CAPSULE
}