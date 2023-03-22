package co.yml.ycoreui.ui.ytag

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ycoreui.ui.ytag.model.TagViewModifiers

@Composable
fun TagView(tagViewModifiers: TagViewModifiers) {
    with(tagViewModifiers) {
        Row(modifier = Modifier
            .run {
                if (enableBorder) {
                    border(
                        width = borderWidth,
                        color = borderColor,
                        shape = tagViewModifiers.shape,
                    )
                } else {
                    background(color = backgroundColor, shape = tagViewModifiers.shape)
                }
            }
            .background(
                color = backgroundColor,
                shape = tagViewModifiers.shape
            ),
            verticalAlignment = Alignment.CenterVertically) {
            leadingIcon?.invoke()

            Text(
                text = tagViewModifiers.text,
                color = tagViewModifiers.textColor,
                fontSize = tagViewModifiers.fontSize,
                modifier = Modifier
                    .padding(
                        startPadding,
                        topPadding,
                        endPadding,
                        bottomPadding
                    )
                    .semantics {
                        this.contentDescription = text
                    },
                style = tagViewModifiers.style
            )

            trailingIcon?.invoke()
        }
    }
}

@Preview(name = "Default Tag")
@Composable
fun DefaultTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("Default")
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with border")
@Composable
fun BorderTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("BorderTag")
        .enableBorder(true)
        .backgroundColor(Color.White)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with background color")
@Composable
fun BackgroundTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("Background")
        .backgroundColor(Color.Red)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Capsule shape tag")
@Composable
fun CapsuleShapeTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("Capsule")
        .backgroundColor(Color.Red)
        .shape(CircleShape)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Rectangle shape tag")
@Composable
fun RectangleShapeTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("RoundRectangle")
        .backgroundColor(Color.Green)
        .shape(RectangleShape)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "RoundRectangle shape tag")
@Composable
fun RoundRectangleShapeTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("RoundRoundRectangle")
        .backgroundColor(Color.Yellow)
        .shape(RoundedCornerShape(8.dp))
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with background color and border")
@Composable
fun BackgroundBorderTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("BackgroundBorder")
        .backgroundColor(Color.Red)
        .enableBorder(true)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with custom padding")
@Composable
fun CustomPaddingTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("With padding")
        .startPadding(8.dp)
        .topPadding(8.dp)
        .endPadding(8.dp)
        .bottomPadding(8.dp)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with leading icon")
@Composable
fun LeadingIconTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("LeadingIcon")
        .leadingIcon {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                    contentDescription = null
                )
            }
        }
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with leading icon")
@Composable
fun TrailingIconTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("LeadingIcon")
        .trailingIcon {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = null
                )
            }
        }
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Preview(name = "Tag with leading and trailing icon")
@Composable
fun LeadingTrailingIconTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("LeadingIcon")
        .leadingIcon {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                    contentDescription = null
                )
            }
        }
        .trailingIcon {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = null
                )
            }
        }
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}
