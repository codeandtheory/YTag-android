package co.yml.ycoreui.ui.ytag.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TagViewModifiers(
    val minWidth: Dp,
    val minHeight: Dp,
    val text: String,
    val textColor: Color,
    val fontSize: TextUnit,
    val fontStyle: FontStyle,
    val fontWeight: FontWeight,
    val fontFamily: FontFamily,
    val letterSpacing: TextUnit,
    val textDecoration: TextDecoration?,
    val textAlign: TextAlign,
    val lineHeight: TextUnit,
    val overflow: TextOverflow,
    val softWrap: Boolean,
    val maxLines: Int,
    val onTextLayout: (TextLayoutResult) -> Unit,
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
        private var minWidth: Dp = 52.dp
        private var minHeight: Dp = 52.dp
        private var text: String = ""
        private var textColor: Color = Color.Black
        private var fontSize: TextUnit = 12.sp
        private var fontStyle: FontStyle = FontStyle.Normal
        private var fontWeight: FontWeight = FontWeight.Normal
        private var fontFamily: FontFamily = FontFamily.Default
        private var letterSpacing: TextUnit = TextUnit.Unspecified
        private var textDecoration: TextDecoration? = null
        private var textAlign: TextAlign = TextAlign.Center
        private var lineHeight: TextUnit = TextUnit.Unspecified
        private var overflow: TextOverflow = TextOverflow.Clip
        private var softWrap: Boolean = true
        private var onTextLayout: (TextLayoutResult) -> Unit = {}
        private var maxLines: Int = Int.MAX_VALUE
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

        fun minWidth(minWidth: Dp) = apply { this.minWidth = minWidth }

        fun minHeight(minHeight: Dp) = apply { this.minHeight = minHeight }
        fun text(text: String) = apply { this.text = text }

        fun textColor(textColor: Color) = apply { this.textColor = textColor }

        fun fontSize(fontSize: TextUnit) = apply { this.fontSize = fontSize }

        fun fontStyle(fontStyle: FontStyle) = apply { this.fontStyle = fontStyle }

        fun fontWeight(fontWeight: FontWeight) = apply { this.fontWeight = fontWeight }

        fun fontFamily(fontFamily: FontFamily) = apply { this.fontFamily = fontFamily }
        fun letterSpacing(letterSpacing: TextUnit) = apply { this.letterSpacing = letterSpacing }
        fun textDecoration(textDecoration: TextDecoration) =
            apply { this.textDecoration = textDecoration }

        fun textAlign(textAlign: TextAlign) = apply { this.textAlign = textAlign }

        fun lineHeight(lineHeight: TextUnit) = apply { this.lineHeight = lineHeight }

        fun overFlow(overflow: TextOverflow) = apply { this.overflow = overflow }

        fun softWrap(softWrap: Boolean) = apply { this.softWrap = softWrap }

        fun maxLines(maxLines: Int) = apply { this.maxLines }

        fun onTextLayout(onTextLayout: (TextLayoutResult) -> Unit) =
            apply { this.onTextLayout = onTextLayout }

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

        fun leadingIcon(leadingIcon: @Composable (() -> Unit)?) =
            apply { this.leadingIcon = leadingIcon }

        fun trailingIcon(trailingIcon: @Composable (() -> Unit)?) =
            apply { this.trailingIcon = trailingIcon }

        fun shape(shape: Shape) = apply { this.shape = shape }

        fun build() = TagViewModifiers(
            minWidth,
            minHeight,
            text,
            textColor,
            fontSize,
            fontStyle,
            fontWeight,
            fontFamily,
            letterSpacing,
            textDecoration,
            textAlign,
            lineHeight,
            overflow,
            softWrap,
            maxLines,
            onTextLayout,
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
