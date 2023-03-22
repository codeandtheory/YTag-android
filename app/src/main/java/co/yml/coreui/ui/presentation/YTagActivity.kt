package co.yml.coreui.ui.presentation

import android.os.Bundle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.coreui.R
import co.yml.coreui.ui.compositions.AppBarWithBackButton
import co.yml.coreui.ui.theme.CoreUICatalogTheme
import co.yml.ycoreui.ui.ytag.TagView
import co.yml.ycoreui.ui.ytag.model.TagViewModifiers

@ExperimentalMaterial3Api
class YTagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoreUICatalogTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
//                    containerColor = YCoreUITheme.colors.background,
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
                                items(8) { item ->
                                    when (item) {
                                        0 -> DefaultTag()
                                        1 -> CapsuleTag()
                                        2 -> RectangleTag()
                                        3 -> RoundRectangleTag()
                                        4 -> RoundRectangleTagWithLeadingIcon()
                                        5 -> RoundRectangleTagWithTrailingIcon()
                                        6 -> RoundRectangleTagWithLeadingTrailingIcon()
                                        7 -> BorderTag()
                                    }
                                }
                            },
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("Default")
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Composable
fun CapsuleTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("Capsule")
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Composable
fun RectangleTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("Rectangle")
        .fontSize(12.sp)
        .shape(RectangleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Composable
fun RoundRectangleTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("RoundRectangle")
        .shape(RoundedCornerShape(4.dp))
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Composable
fun RoundRectangleTagWithLeadingIcon() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("RoundRectangle")
        .shape(RoundedCornerShape(4.dp))
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .leadingIcon {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}


@Composable
fun RoundRectangleTagWithTrailingIcon() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("RoundRectangle")
        .shape(RoundedCornerShape(4.dp))
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .trailingIcon {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Composable
fun RoundRectangleTagWithLeadingTrailingIcon() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("RoundRectangle")
        .shape(RoundedCornerShape(4.dp))
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .leadingIcon {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        .trailingIcon {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}

@Composable
fun BorderTag() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("BorderTag")
        .textColor(Color.Black)
        .enableBorder(true)
        .borderColor(Color.Red)
        .backgroundColor(Color.White)
        .shape(CircleShape)
        .startPadding(16.dp)
        .endPadding(16.dp)
        .topPadding(8.dp)
        .bottomPadding(8.dp)
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}
