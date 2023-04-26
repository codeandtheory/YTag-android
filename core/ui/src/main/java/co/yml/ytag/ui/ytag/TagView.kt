package co.yml.ytag.ui.ytag

import android.R
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import co.yml.ytag.ui.ytag.model.TagViewData
import co.yml.ytag.ui.ytag.model.TagViewModifiers

/**
 * [TagView] Compose method used for creating a custom chip
 *
 * @param text text the text to be displayed
 * @param leadingIcon the optional leading icon to be displayed at the beginning of the TagView
 * @param trailingIcon the optional leading icon to be displayed at the end of the TagView
 * @param enabled controls the enabled state of the TagView
 * @param tagViewModifiers collection of modifier elements that decorate or add behavior to TagView elements
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun TagView(
    text: String,
    leadingIcon: @Composable ((tagViewData: TagViewData) -> Unit)? = null,
    trailingIcon: @Composable ((tagViewData: TagViewData) -> Unit)? = null,
    enabled: Boolean = true,
    tagViewModifiers: TagViewModifiers = TagViewModifiers.Builder().build(),
    overFlowText: String = ""
) {
    val tagViewData = TagViewData(
        text = text,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        enabled = enabled,
        tagViewModifiers = tagViewModifiers,
        overFlowText = { overFlowText }
    )

    // used for visibility animation
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    // used for alpha animation
    var tagVisible by remember {
        mutableStateOf(true)
    }

    val tagAlpha: Float by animateFloatAsState(
        targetValue = if (tagVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = tagViewModifiers.alphaAnimation.durationMillis,
            easing = LinearEasing
        ),
        finishedListener = {
            // delegate the click event once remove animation is completed
            tagViewModifiers.onClick.invoke(tagViewData)
        }
    )

    with(tagViewModifiers) {
        AnimatedVisibility(
            visibleState = state,
            enter = fadeIn(animationSpec = tween(alphaAnimation.durationMillis)),
            exit = fadeOut(animationSpec = tween(alphaAnimation.durationMillis))
        ) {
            Surface(
                shadowElevation = shadowElevation,
                tonalElevation = tonalElevation,
                shape = shape,
                modifier = Modifier
                    .testTag("tag_view")
                    .width(width = width)
                    .height(height = height)
                    .alpha(tagAlpha)
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .width(width = width)
                        .height(height)
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
                                if (tagViewModifiers.alphaAnimation.enabled) {
                                    tagVisible = false
                                    state.targetState = false
                                } else {
                                    tagViewModifiers.onClick.invoke(tagViewData)
                                }
                            }
                        }
                        .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
                        .padding(containerPaddingValues)
                        .background(
                            color = backgroundColor,
                            shape = shape
                        )

                ) {
                    val (leading_icon, text_view, trailing_icon) = createRefs()

                    Box(
                        modifier = Modifier
                            .constrainAs(leading_icon) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        leadingIcon?.invoke(tagViewData)
                    }

                    Text(
                        text = overFlowText.ifEmpty { text },
                        color = textColor,
                        fontSize = fontSize,
                        fontWeight = fontWeight,
                        fontFamily = fontFamily,
                        fontStyle = fontStyle,
                        letterSpacing = letterSpacing,
                        modifier = Modifier
                            .constrainAs(text_view) {
                                start.linkTo(leading_icon.end)
                                end.linkTo(trailing_icon.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                            .padding(
                                textPadding
                            )
                            .semantics {
                                this.contentDescription = semantics
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
                    Box(
                        modifier = Modifier.constrainAs(trailing_icon) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                    ) {
                        trailingIcon?.invoke(tagViewData)
                    }
                }
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
