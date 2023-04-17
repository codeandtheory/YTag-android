package co.yml.coreui.core.ui.ytag.model

import androidx.compose.foundation.layout.PaddingValues
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


/**
 * [TagViewModifiers] Represents immutable collection of modifier elements that decorate or add behavior to TagView elements.
 * - If a parameter is explicitly set here then that parameter will always be used.
 * - If a parameter is not set then the corresponding default value will be used
 *
 * @param minWidth define a default min width of TagView
 * @param minHeight define a default min height of TagView
 * @param width define width of TagView
 * @param height define height of TagView
 * @param textColor apply color to the text
 * @param fontSize define fontSize of the text element
 * @param fontStyle define fontStyle of the text element
 * @param fontFamily define fontStyle of the text element
 * @param letterSpacing the amount of space to add between each letter in the text element
 * @param textDecoration the decorations to paint on the text element
 * @param textAlign the alignment of the text within the lines of the paragraph
 * @param lineHeight line height for the Paragraph in TextUnit uni
 * @param overflow how visual overflow should be handled.
 * @param softWrap whether the text should break at soft line breaks.
 * @param maxLines an optional maximum number of lines for the text to span, wrapping if necessary.
 * @param onTextLayout callback that is executed when a new text layout is calculated.
 * @param style style configuration for the text such as color, font, line height etc.
 * @param enableBorder enable border for TagView
 * @param borderWidth define borderWidth of the TagView
 * @param borderColor define borderColor of the TagView
 * @param backgroundColor define backgroundColor of the TagView
 * @param textPadding define padding for TagView text component
 * @param shape defines the shape of the TagView
 * @param tonalElevation When color is ColorScheme.surface, a higher the elevation will result in a darker color in light theme and lighter color in dark theme.
 * @param shadowElevation  The size of the shadow below the surface.
 * @param containerPaddingValues define padding for TagView
 * @param onClick perform click event
 * @param semantics add content description for tag view
 */
data class TagViewModifiers(
    val minWidth: Dp,
    val minHeight: Dp,
    val width: Dp,
    val height: Dp,
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
    val textPadding: PaddingValues,
    val shape: Shape,
    val tonalElevation: Dp,
    val shadowElevation: Dp,
    val containerPaddingValues: PaddingValues,
    val onClick: (TagViewData) -> Unit,
    val semantics: String,
    val alphaAnimation: AlphaAnimation

) {
    class Builder {
        private var minWidth: Dp = 80.dp
        private var minHeight: Dp = 32.dp
        private var width: Dp = minWidth
        private var height: Dp = minHeight
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
        private var textPadding: PaddingValues = PaddingValues(horizontal = 8.dp)
        private var shape: Shape = RectangleShape
        private var tonalElevation: Dp = 0.dp
        private var shadowElevation: Dp = 0.dp
        private var containerPaddingValues: PaddingValues = PaddingValues(horizontal = 4.dp)
        private var onClick: (TagViewData) -> Unit = {}
        private var semantics: String = text
        private var alphaAnimation: AlphaAnimation = AlphaAnimation()


        fun minWidth(minWidth: Dp) = apply { this.minWidth = minWidth }

        fun minHeight(minHeight: Dp) = apply { this.minHeight = minHeight }

        fun width(width: Dp) = apply { this.width = width }
        fun height(height: Dp) = apply { this.height = height }
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

        fun textPadding(textPadding: PaddingValues) = apply { this.textPadding = textPadding }

        fun shape(shape: Shape) = apply { this.shape = shape }

        fun tonalElevation(tonalElevation: Dp) = apply { this.tonalElevation = tonalElevation }

        fun shadowElevation(shadowElevation: Dp) = apply { this.shadowElevation = shadowElevation }
        fun containerPaddingValues(paddingValues: PaddingValues) =
            apply { this.containerPaddingValues = paddingValues }

        fun onCLick(onClick: (TagViewData) -> Unit) = apply { this.onClick = onClick }

        fun semantics(semantics: String) = apply { this.semantics = semantics }

        fun alphaAnimation(alphaAnimation: AlphaAnimation) = apply { this.alphaAnimation = alphaAnimation }
        fun build() = TagViewModifiers(
            minWidth,
            minHeight,
            width,
            height,
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
            textPadding,
            shape,
            tonalElevation,
            shadowElevation,
            containerPaddingValues,
            onClick,
            semantics,
            alphaAnimation
        )
    }
}
