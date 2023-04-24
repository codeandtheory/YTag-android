package co.yml.coreui.core.ui.ystepper

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import co.yml.coreui.core.ui.ystepper.model.StepperIcon
import co.yml.coreui.core.ui.ystepper.model.StepperModifiers
import co.yml.coreui.ui.R

@Composable
fun StepperView(
    textView: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    deleteIcon: @Composable (() -> Unit)? = null,
    visible: Boolean = true,
    stepperModifier: StepperModifiers = StepperModifiers.Builder().build(),
) {
    val context = LocalContext.current
    with(stepperModifier) {
        var modifiers = if (width == Dp.Unspecified) {
            Modifier.fillMaxWidth()
        } else {
            Modifier.width(width)
        }

        modifiers = if (height == Dp.Unspecified) {
            modifiers.then(Modifier.fillMaxWidth())
        } else {
            modifiers.then(Modifier.height(height))
        }

        modifiers = Modifier
            .width(width)
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
            }
            .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
            .padding(containerPaddingValues)
            .background(
                color = backgroundColor,
                shape = shape
            )

        if (visible) {
            Surface(
                shadowElevation = shadowElevation,
                tonalElevation = tonalElevation,
                shape = shape,
                modifier = Modifier
                    .testTag("stepper_view")
                    .semantics {
                        this.contentDescription = stepperViewSemantics ?: context.getString(R.string.stepper_view_accessibility)
                    }
            ) {
                ConstraintLayout(
                    modifier = modifiers
                ) {
                    val (leading_icon, text_view, trailing_icon) = createRefs()

                    //Leading Icon
                    if (showDeleteIcon) {
                        //Delete Icon
                        deleteIcon?.let {
                            Box(
                                modifier = Modifier
                                    .testTag("delete_icon_custom")
                                    .constrainAs(leading_icon) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                            ) {
                                deleteIcon.invoke()
                            }
                        } ?: kotlin.run {
                            Box(
                                modifier = Modifier
                                    .testTag("delete_icon_default")
                                    .constrainAs(leading_icon) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                            ) {
                                IconButton(enabled = stepperModifier.deleteIcon.enable, onClick = {
                                    stepperModifier.deleteIcon.onClickListener.invoke()
                                }) {
                                    Icon(
                                        painter =  painterResource(id = stepperModifier.deleteIcon.icon ?:  R.drawable.ic_delete_20px),
                                        tint = stepperModifier.deleteIcon.iconTint,
                                        contentDescription = stepperModifier.deleteIcon.semantics ?: stringResource(
                                            id = R.string.ic_delete_accessibility
                                        )
                                    )
                                }
                            }
                        }
                    } else {
                        leadingIcon?.let {
                            Box(
                                modifier = Modifier
                                    .testTag("leading_icon_custom")
                                    .constrainAs(leading_icon) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                            ) {
                                leadingIcon.invoke()
                            }
                        } ?: kotlin.run {
                            Box(
                                modifier = Modifier
                                    .testTag("leading_icon_default")
                                    .constrainAs(leading_icon) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    }
                            ) {
                                IconButton(enabled = stepperModifier.leadingIcon.enable, onClick = {
                                    stepperModifier.leadingIcon.onClickListener.invoke()
                                }, modifier = Modifier.testTag("leading_icon_button_default")) {
                                    Icon(
                                        painter = painterResource(id = stepperModifier.leadingIcon.icon ?:  R.drawable.ic_remove_20px),
                                        tint = stepperModifier.leadingIcon.iconTint,
                                        contentDescription = stepperModifier.leadingIcon.semantics ?: stringResource(
                                            id = R.string.ic_remove_accessibility
                                        )
                                    )
                                }
                            }
                        }
                    }

                    //Text view
                    textView?.let {
                        Box(modifier = Modifier
                            .testTag("text_view_custom")
                            .constrainAs(text_view) {
                                start.linkTo(leading_icon.end)
                                end.linkTo(trailing_icon.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
//                        width = Dimension.fillToConstraints
                            }) {
                            textView.invoke()
                        }
                    } ?: kotlin.run {
                        Box(modifier = Modifier
                            .testTag("text_view_default")
                            .constrainAs(text_view) {
                                start.linkTo(leading_icon.end)
                                end.linkTo(trailing_icon.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
//                        width = Dimension.fillToConstraints
                            }) {
                            Text(
                                text = text,
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
                                    .semantics {
                                        this.contentDescription = textViewSemantics
                                    },
                                style = style,
                                textAlign = textAlign,
                                lineHeight = lineHeight,
                                overflow = overflow,
                                softWrap = softWrap,
                                maxLines = maxLines,
                                onTextLayout = onTextLayout
                            )
                        }
                    }

                    //Trailing Icon
                    trailingIcon?.let {
                        Box(
                            modifier = Modifier
                                .testTag("trailing_icon_custom")
                                .constrainAs(trailing_icon) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                        ) {
                            trailingIcon.invoke()
                        }
                    } ?: kotlin.run {
                        Box(
                            modifier = Modifier
                                .testTag("trailing_icon_default")
                                .constrainAs(trailing_icon) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                        ) {
                            IconButton(enabled = stepperModifier.trailingIcon.enable, onClick = {
                                stepperModifier.trailingIcon.onClickListener.invoke()
                            },modifier = Modifier.testTag("trailing_icon_button_default")) {
                                Icon(
                                    painter = painterResource(id = stepperModifier.trailingIcon.icon ?:  R.drawable.ic_add_20px),
                                    tint = stepperModifier.trailingIcon.iconTint,
                                    contentDescription = stepperModifier.trailingIcon.semantics ?: stringResource(
                                        id = R.string.ic_add_accessibility
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Default Stepper")
@Composable
fun DefaultStepper() {
    val stepperModifiers = StepperModifiers.Builder()
        .width(120.dp)
        .height(36.dp)
        .text("1")
        .textColor(Color.Black)
        .build()

    StepperView(stepperModifier = stepperModifiers)
}

@Preview(name = "Capsule Stepper")
@Composable
fun CapsuleStepper() {
    val stepperModifiers = StepperModifiers.Builder()
        .width(120.dp)
        .height(36.dp)
        .text("1")
        .textColor(Color.Black)
        .shape(CircleShape)
        .build()

    StepperView(stepperModifier = stepperModifiers)
}

@Preview(name = "Capsule Stepper with border")
@Composable
fun BorderStepper() {
    val stepperModifiers = StepperModifiers.Builder()
        .width(120.dp)
        .height(36.dp)
        .text("1")
        .textColor(Color.Black)
        .shape(CircleShape)
        .enableBorder(true)
        .borderColor(Color.Red)
        .build()

    StepperView(stepperModifier = stepperModifiers)
}

@Preview(name = "Capsule Stepper with background")
@Composable
fun BackgroundStepper() {
    val stepperModifiers = StepperModifiers.Builder()
        .width(120.dp)
        .height(36.dp)
        .text("1")
        .textColor(Color.Black)
        .shape(CircleShape)
        .backgroundColor(Color.Yellow)
        .build()

    StepperView(stepperModifier = stepperModifiers)
}

@Preview(name = "Capsule Stepper with delete icon")
@Composable
fun DeleteIconStepper() {
    val stepperModifiers = StepperModifiers.Builder()
        .width(120.dp)
        .height(36.dp)
        .text("1")
        .textColor(Color.Black)
        .shape(CircleShape)
        .showDeleteIcon(true)
        .build()

    StepperView(stepperModifier = stepperModifiers)
}

@Preview(name = "Capsule Stepper with custom icons")
@Composable
fun CustomIconStepper() {
    val stepperModifiers = StepperModifiers.Builder()
        .width(120.dp)
        .height(36.dp)
        .text("5")
        .textColor(Color.Black)
        .shape(CircleShape)
        .leadingIcon(
            leadingIcon = StepperIcon(icon = android.R.drawable.star_on, iconTint = Color.Black)
        )
        .trailingIcon(trailingIcon = StepperIcon(icon = android.R.drawable.star_off, iconTint = Color.Black))
        .build()

    StepperView(stepperModifier = stepperModifiers)
}
