package co.yml.coreui.feature.ytag.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import co.yml.coreui.core.ui.templates.AppBarWithBackButton
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme
import co.yml.coreui.core.ui.ytag.model.TagViewModifiers
import co.yml.coreui.ui.R
import co.yml.coreui.core.ui.ytag.TagView
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class YTagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoreUICatalogTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_y_tag),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(
                            content = {
                                items(9) { item ->
                                    when (item) {
                                        0 -> CapsuleTag()
                                        1 -> RectangleTag()
                                        2 -> RoundRectangleTag()
                                        3 -> DefaultTag()
                                        4 -> TagWithLeadingIcon()
                                        5 -> TagWithTrailingIcon()
                                        6 -> TagWithLeadingTrailingIcon()
                                        7 -> BorderTag()
                                        8 -> ShadowTag()
                                    }
                                }
                            },
                            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal)),
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_normal_medium))
                        )
                    }
                }
            }
        }
    }
}

val textStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = FontFamily.SansSerif,
)

@Composable
fun DefaultTag() {
    TagView(text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_default))
}

@Composable
fun CapsuleTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .style(textStyle)
        .build()

    TagView(
        text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_capsule),
        tagViewModifiers = tagViewModifiers
    )
}

@Composable
fun RectangleTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(RectangleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .style(textStyle)
        .build()

    TagView(
        text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_rectangle),
        tagViewModifiers = tagViewModifiers
    )
}

@Composable
fun RoundRectangleTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)))
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .style(textStyle)
        .build()

    TagView(
        text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_round_rectangle),
        tagViewModifiers = tagViewModifiers
    )
}

@Composable
fun TagWithLeadingIcon() {
    val context = LocalContext.current
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .fontStyle(FontStyle.Italic)
        .build()
    val text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_leading_icon)
    TagView(text = text, leadingIcon = { enabled ->
        IconButton(
            modifier = Modifier.size(dimensionResource(id = R.dimen.padding_normal_medium)),
            onClick = {
                if (enabled) {
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                }
            }) {
            Icon(
                painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_location_24px),
                contentDescription = null,
                tint = Color.White
            )
        }
    }, tagViewModifiers = tagViewModifiers)
}


@Composable
fun TagWithTrailingIcon() {
    val context = LocalContext.current
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .fontSize(15.sp)
        .build()

    val text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_trailing_icon)
    TagView(text = text, trailingIcon = { enabled ->
        IconButton(modifier = Modifier
            .padding(end = dimensionResource(id = R.dimen.padding_medium))
            .size(dimensionResource(id = R.dimen.padding_normal_medium)), onClick = {
            if (enabled) {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            }
        }) {
            Icon(
                painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_close_20px),
                contentDescription = null,
                tint = Color.White
            )
        }
    }, tagViewModifiers = tagViewModifiers)
}

@Composable
fun TagWithLeadingTrailingIcon() {
    val context = LocalContext.current
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .maxLines(1)
        .overFlow(TextOverflow.Ellipsis)
        .textColor(Color.White)
        .onCLick {

        }
        .build()

    TagView(
        text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_leading_trailing_icon),
        leadingIcon = { enabled ->
            val text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_leading_icon)
            IconButton(
                modifier = Modifier.size(dimensionResource(id = R.dimen.padding_normal_medium)),
                onClick = {
                    if (enabled) {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    }
                }) {
                Icon(
                    painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_location_24px),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        trailingIcon = { enabled ->
            val text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_trailing_icon)
            IconButton(modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_medium))
                .size(dimensionResource(id = R.dimen.padding_normal_small)), onClick = {
                if (enabled) {
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(
                    painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_close_20px),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        tagViewModifiers = tagViewModifiers,
        enabled = false
    )
}

@Composable
fun BorderTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .textColor(Color.Black)
        .enableBorder(true)
        .borderColor(Color.Red)
        .borderWidth(dimensionResource(id = R.dimen.padding_very_tiny))
        .backgroundColor(Color.White)
        .shape(CircleShape)
        .style(textStyle)
        .build()

    TagView(
        text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_border),
        tagViewModifiers = tagViewModifiers
    )
}

@Composable
fun ShadowTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .textColor(colorResource(id = co.yml.coreui.feature.ytag.R.color.tag_text_color))
        .backgroundColor(colorResource(id = co.yml.coreui.feature.ytag.R.color.tag_background_color))
        .shape(CircleShape)
        .shadowElevation(dimensionResource(id = R.dimen.padding_tiny))
        .style(textStyle)
        .maxLines(1)
        .overFlow(TextOverflow.Ellipsis)
        .build()

    TagView(
        text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_shadow),
        tagViewModifiers = tagViewModifiers
    )
}