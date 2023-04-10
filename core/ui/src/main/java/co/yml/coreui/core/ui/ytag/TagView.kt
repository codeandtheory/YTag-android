package co.yml.coreui.core.ui.ytag

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.coreui.core.ui.ytag.model.TagViewData
import co.yml.coreui.core.ui.ytag.model.TagViewModifiers

/**
 * [TagView] Compose method used for creating a custom chip
 *
 * @param text text the text to be displayed
 * @param leadingIcon the optional leading icon to be displayed at the beginning of the TagView
 * @param trailingIcon the optional leading icon to be displayed at the end of the TagView
 * @param enabled controls the enabled state of the TagView
 * @param tagViewModifiers collection of modifier elements that decorate or add behavior to TagView elements
 */
@Composable
fun TagView(
    text: String,
    leadingIcon: @Composable ((enable: Boolean) -> Unit)? = null,
    trailingIcon: @Composable ((enable: Boolean) -> Unit)? = null,
    enabled: Boolean = true,
    tagViewModifiers: TagViewModifiers = TagViewModifiers.Builder().build(),
    overFlowText: String = "",
    onClick: () -> Unit = {}
) {
    with(tagViewModifiers) {
        Surface(
            shadowElevation = shadowElevation,
            tonalElevation = tonalElevation,
            shape = shape,
            modifier = Modifier
                .testTag("tag_view")
                .width(width = width ?: Dp.Unspecified)
                .height(height = height)
        ) {
            Row(
                modifier = Modifier
                    .run {
                        if (enableBorder) {
                            border(
                                width = borderWidth,
                                color = borderColor,
                                shape = shape
                            )
                        } else {
                            background(color = backgroundColor, shape = shape)
                        }
                    }
                    .clickable {
                        if (enabled) {
                            onClick.invoke()
                            tagViewModifiers.onClick.invoke()
                        }
                    }
                    .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
                    .padding(containerPaddingValues)
                    .background(
                        color = backgroundColor,
                        shape = shape
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingIcon?.invoke(enabled)

                Text(
                    text = overFlowText.ifEmpty { text },
                    color = textColor,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    fontStyle = fontStyle,
                    letterSpacing = letterSpacing,
                    modifier = Modifier
                        .padding(
                            textPadding
                        )
                        .align(Alignment.CenterVertically)
                        .semantics {
                            this.contentDescription = text
                        },
                    style = style,
                    textDecoration = textDecoration,
                    textAlign = textAlign,
                    lineHeight = lineHeight,
                    overflow = overflow,
                    softWrap = softWrap,
                    maxLines = maxLines,
                    onTextLayout = onTextLayout
                )
                trailingIcon?.invoke(enabled)
            }
        }
    }
}

@Preview(name = "Default Tag")
@Composable
fun DefaultTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .build()

    TagView(text = "Default", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with border")
@Composable
fun BorderTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .enableBorder(true)
        .backgroundColor(Color.White)
        .build()

    TagView(text = "BorderTag", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with background color")
@Composable
fun BackgroundTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .backgroundColor(Color.Red)
        .build()

    TagView(text = "Background", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Capsule shape tag")
@Composable
fun CapsuleShapeTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .backgroundColor(Color.Red)
        .shape(CircleShape)
        .build()

    TagView(text = "Capsule", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Rectangle shape tag")
@Composable
fun RectangleShapeTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .backgroundColor(Color.Green)
        .shape(RectangleShape)
        .build()

    TagView(text = "Rectangle", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "RoundRectangle shape tag")
@Composable
fun RoundRectangleShapeTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .backgroundColor(Color.Yellow)
        .shape(RoundedCornerShape(8.dp))
        .build()

    TagView(text = "RoundRectangle", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with background color and border")
@Composable
fun BackgroundBorderTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .backgroundColor(Color.Red)
        .enableBorder(true)
        .build()

    TagView(text = "BackgroundBorder", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with custom padding")
@Composable
fun CustomPaddingTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .textPadding(PaddingValues(16.dp))
        .build()

    TagView(text = "With padding", tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with leading icon")
@Composable
fun LeadingIconTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .build()

    TagView(text = "LeadingIcon", leadingIcon = {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_mylocation),
                contentDescription = null
            )
        }
    }, tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with leading icon")
@Composable
fun TrailingIconTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .build()

    TagView(text = " TrailingIcon", trailingIcon = {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_close_clear_cancel),
                contentDescription = null
            )
        }
    }, tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with leading and trailing icon")
@Composable
fun LeadingTrailingIconTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .build()

    TagView(text = "LeadingTrailingIcon", leadingIcon = {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_mylocation),
                contentDescription = null
            )
        }
    }, trailingIcon = {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_close_clear_cancel),
                contentDescription = null
            )
        }
    }, tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with min width and height")
@Composable
fun MinWidthHeightTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .fontSize(10.sp)
        .backgroundColor(Color.White)
        .enableBorder(true)
        .shape(CircleShape)
        .borderColor(Color.Black)
        .minWidth(60.dp)
        .minHeight(20.dp)
        .build()

    TagView(text = "Min Max Width", tagViewModifiers = tagViewModifiers)
}
