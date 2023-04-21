package co.yml.coreui.feature.ytag.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.coreui.core.ui.templates.AppBarWithBackButton
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme
import co.yml.coreui.core.ui.ytag.TagViewContainer
import co.yml.coreui.core.ui.ytag.model.AlphaAnimation
import co.yml.coreui.core.ui.ytag.model.TagViewContainerModifiers
import co.yml.coreui.core.ui.ytag.model.TagViewData
import co.yml.coreui.core.ui.ytag.model.TagViewModifiers
import co.yml.coreui.ui.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@ExperimentalMaterial3Api
@AndroidEntryPoint
class YTagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoreUICatalogTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CoreUICatalogTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(stringResource(id = R.string.title_y_tag),
                            onBackPressed = {
                                onBackPressed()
                            })
                    }) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(it)
                            .padding(PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_normal)))
                    ) {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal_medium)))

                        CustomTagViewContainer()

                        DefaultTagViewContainer()
                    }
                }
            }
        }
    }
}

val textStyle = TextStyle(
    fontSize = 14.sp, fontFamily = FontFamily.SansSerif
)


@Composable
fun DefaultTagViewContainer() {
    val context = LocalContext.current
    val tagViewData = remember {
        mutableStateListOf<TagViewData>()
    }
    tagViewData.addAll(
        (listOf(
            capsuleTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_blue_300),
                textColor = Color.Black
            ),

            rectangleTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_blue_200),
                textColor = Color.Black
            ),

            roundRectTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_blue_300),
                textColor = Color.Black
            ),

            leadingIconTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_blue_200),
                textColor = Color.Black,
                iconTint = Color.Black
            ),

            trailingIconData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_blue_300),
                textColor = Color.Black,
                iconTint = Color.Black
            ),

            leadingIconTrailingIconData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_blue_200),
                textColor = Color.Black,
                iconTint = Color.Black
            )
        ))
    )

    DefaultTagViewContainer(tagViewData = tagViewData)
}

@Composable
fun DefaultTagViewContainer(tagViewData: MutableList<TagViewData>) {
    val context = LocalContext.current

    val tagViewContainerModifiers =
        TagViewContainerModifiers.Builder().containerPaddingValues(PaddingValues(8.dp))
            .enableBorder(true).shape(RoundedCornerShape(4.dp)).tagSpacingVertical(8.dp)
            .tagSpacingHorizontal(8.dp).backgroundColor(colorResource(id = R.color.cyan_50))
            .width(360.dp).height(50.dp).moreTagConfiguration(
                TagViewData(
                    overFlowText = { count ->
                        "+ $count more"
                    },
                    tagViewModifiers = TagViewModifiers.Builder()
                        .backgroundColor(colorResource(id = R.color.light_blue_300))
                        .shape(CircleShape).width(80.dp).textAlign(TextAlign.Start).height(30.dp)
                        .maxLines(1).overFlow(TextOverflow.Ellipsis).textAlign(TextAlign.Center)
                        .textColor(Color.Black).fontWeight(FontWeight.Medium).onCLick { }.build()
                )
            ).onCLick { item ->
                // tag view item click
                repeat(tagViewData.filter { it == item }.size) {
                    tagViewData.remove(item)
                }

            }.build()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(32.dp))



        TagViewContainer(
            tagViewData = tagViewData, tagViewContainerModifiers = tagViewContainerModifiers
        )

        Spacer(modifier = Modifier.height(16.dp))

        val tagColor = colorResource(id = R.color.light_blue_200)
        Button(
            onClick = {
                val tagData = TagViewData(
                    text = "${context.getString(co.yml.coreui.feature.ytag.R.string.tag_capsule)} ${
                        Random.nextInt(
                            1, 20
                        )
                    }",
                    tagViewModifiers = TagViewModifiers.Builder().width(90.dp).shape(CircleShape)
                        .backgroundColor(tagColor).textColor(Color.Black).style(textStyle).build()
                )
                tagViewData.add(tagData)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        ) {
            Text(text = "Add Tag View")
        }

    }
}

@Composable
fun CustomTagViewContainer() {

    val context = LocalContext.current
    val tagViewData = mutableListOf<TagViewData>()

    tagViewData.addAll(
        (listOf(
            capsuleTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.russian_violet),
                textColor = Color.White
            ),

            rectangleTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_green),
                textColor = Color.Black
            ),

            roundRectTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.light_yellow),
                textColor = Color.Black
            ),
            TagViewData(text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_default)),

            leadingIconTagData(
                context = context,
                backgroundColor = colorResource(id = R.color.bitter_sweet),
                textColor = Color.White,
                iconTint = Color.White
            ),

            trailingIconData(
                context = context,
                backgroundColor = colorResource(id = R.color.power),
                textColor = Color.White,
                iconTint = Color.White
            ),

            leadingIconTrailingIconData(
                context = context, backgroundColor = Color.Black, textColor = Color.White
            ),

            borderTagData(
                context = context,
                backgroundColor = Color.White,
                textColor = Color.Black
            ),

            shadowTagData(
                context = context,
                backgroundColor = colorResource(id = co.yml.coreui.feature.ytag.R.color.tag_background_color),
                textColor = colorResource(id = co.yml.coreui.feature.ytag.R.color.tag_text_color)
            )
        ))
    )


    CustomTagViewContainer(tagViewData = tagViewData)
}

@Composable
fun CustomTagViewContainer(tagViewData: MutableList<TagViewData>) {
    val tagViewContainerModifiers =
        TagViewContainerModifiers.Builder().containerPaddingValues(PaddingValues(4.dp))
            .shape(RoundedCornerShape(4.dp))
            .tagSpacingVertical(dimensionResource(id = R.dimen.padding_normal))
            .tagSpacingHorizontal(dimensionResource(id = R.dimen.padding_normal))
            .moreTagConfiguration(
                TagViewData(
                    overFlowText = { count ->
                        "+ $count more"
                    },
                    tagViewModifiers = TagViewModifiers.Builder()
                        .backgroundColor(colorResource(id = R.color.light_blue_300))
                        .shape(CircleShape).width(80.dp).textAlign(TextAlign.Start).height(30.dp)
                        .maxLines(1).overFlow(TextOverflow.Ellipsis).textAlign(TextAlign.Center)
                        .textColor(Color.Black).fontWeight(FontWeight.Medium).onCLick { }.build()
                )
            ).onCLick { item ->

            }.build()

    TagViewContainer(
        tagViewData = tagViewData, tagViewContainerModifiers = tagViewContainerModifiers
    )
}

/**
 * @param context current context
 * @param backgroundColor tag view background color
 */
fun capsuleTagData(context: Context, backgroundColor: Color, textColor: Color): TagViewData {
    return TagViewData(
        text = context.getString(co.yml.coreui.feature.ytag.R.string.tag_capsule),
        tagViewModifiers = TagViewModifiers.Builder().width(90.dp).shape(CircleShape)
            .backgroundColor(backgroundColor).textColor(textColor).style(textStyle).alphaAnimation(
                AlphaAnimation(enabled = true)
            ).build()
    )
}

/**
 * @param context current context
 * @param backgroundColor tag view background color
 * @param textColor tag view text color
 */
fun rectangleTagData(context: Context, backgroundColor: Color, textColor: Color): TagViewData {
    return TagViewData(
        text = context.getString(co.yml.coreui.feature.ytag.R.string.tag_rectangle),
        tagViewModifiers = TagViewModifiers.Builder().width(90.dp).shape(RectangleShape)
            .backgroundColor(backgroundColor).textColor(textColor).style(textStyle).alphaAnimation(
                AlphaAnimation(enabled = true)
            ).build()
    )
}

/**
 * @param context current context
 * @param backgroundColor tag view background color
 * @param textColor tag view text color
 */
fun roundRectTagData(context: Context, backgroundColor: Color, textColor: Color): TagViewData {
    return TagViewData(
        text = context.getString(co.yml.coreui.feature.ytag.R.string.tag_round_rectangle),
        tagViewModifiers = TagViewModifiers.Builder()
            .shape(RoundedCornerShape(context.resources.getDimension(R.dimen.padding_small)))
            .width(140.dp).backgroundColor(backgroundColor).textColor(textColor).style(textStyle)
            .alphaAnimation(
                AlphaAnimation(enabled = true)
            )
            .build()
    )
}

/**
 * @param context current context
 * @param backgroundColor tag view background color
 * @param textColor tag view text color
 */
fun leadingIconTagData(
    context: Context, backgroundColor: Color, textColor: Color, iconTint: Color = Color.White
): TagViewData {
    return TagViewData(text = context.getString(co.yml.coreui.feature.ytag.R.string.tag_leading_icon),
        tagViewModifiers = TagViewModifiers.Builder().width(120.dp).maxLines(1)
            .overFlow(TextOverflow.Ellipsis).shape(CircleShape).backgroundColor(backgroundColor)
            .textColor(textColor).fontStyle(FontStyle.Italic).alphaAnimation(
                AlphaAnimation(enabled = true)
            ).build(),
        leadingIcon = { tagViewData ->
            IconButton(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_normal_medium)),
                onClick = {
                    if (tagViewData.enabled) {
                        Toast.makeText(
                            context, tagViewData.text, Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                Icon(
                    painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_location_24px),
                    contentDescription = null,
                    tint = iconTint
                )
            }
        })
}

/**
 * @param context current context
 * @param backgroundColor tag view background color
 * @param textColor tag view text color
 */
@Composable
fun trailingIconData(
    context: Context, backgroundColor: Color, textColor: Color, iconTint: Color = Color.White
): TagViewData {
    return TagViewData(text = context.getString(co.yml.coreui.feature.ytag.R.string.tag_trailing_icon),
        tagViewModifiers = TagViewModifiers.Builder().width(150.dp).maxLines(1)
            .textAlign(TextAlign.Start).overFlow(TextOverflow.Ellipsis).shape(CircleShape)
            .backgroundColor(backgroundColor).textColor(textColor).fontSize(15.sp).alphaAnimation(
                AlphaAnimation(enabled = true)
            ).build(),
        trailingIcon = { tagViewData ->
            IconButton(modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_medium))
                .size(dimensionResource(id = R.dimen.padding_normal_medium)), onClick = {
                if (tagViewData.enabled) {
                    Toast.makeText(
                        context, tagViewData.text, Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Icon(
                    painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_close_20px),
                    contentDescription = null,
                    tint = iconTint
                )
            }
        })
}

/**
 * @param context current context
 * @param backgroundColor tag view background color
 * @param textColor tag view text color
 */
fun leadingIconTrailingIconData(
    context: Context, backgroundColor: Color, textColor: Color, iconTint: Color = Color.White
): TagViewData {
    return TagViewData(text = context.getString(co.yml.coreui.feature.ytag.R.string.tag_leading_trailing_icon),
        tagViewModifiers = TagViewModifiers.Builder().width(140.dp).maxLines(1)
            .overFlow(TextOverflow.Ellipsis).shape(CircleShape).backgroundColor(backgroundColor)
            .maxLines(1).overFlow(TextOverflow.Ellipsis).textColor(textColor).onCLick {}
            .alphaAnimation(
                AlphaAnimation(enabled = true)
            ).build(),
        leadingIcon = { tagViewData ->
            IconButton(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_normal_medium)),
                onClick = {
                    if (tagViewData.enabled) {
                        Toast.makeText(context, tagViewData.text, Toast.LENGTH_SHORT).show()
                    }
                }) {
                Icon(
                    painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_location_24px),
                    contentDescription = null,
                    tint = iconTint
                )
            }
        },
        trailingIcon = { tagViewData ->
            IconButton(modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_medium))
                .size(dimensionResource(id = R.dimen.padding_normal_small)), onClick = {
                if (tagViewData.enabled) {
                    Toast.makeText(context, tagViewData.text, Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(
                    painter = painterResource(id = co.yml.coreui.feature.ytag.R.drawable.ic_close_20px),
                    contentDescription = null,
                    tint = iconTint
                )
            }
        })
}

@Composable
fun borderTagData(
    context: Context,
    backgroundColor: Color,
    textColor: Color
): TagViewData {
    val tagViewModifiers = TagViewModifiers.Builder().width(100.dp).textColor(textColor)
        .enableBorder(true)
        .backgroundColor(backgroundColor).shape(CircleShape)
        .borderColor(Color.Red)
        .borderWidth(dimensionResource(id = R.dimen.padding_very_tiny))
        .style(textStyle).maxLines(1)
        .overFlow(TextOverflow.Ellipsis).alphaAnimation(
            AlphaAnimation(enabled = true)
        ).build()

    return TagViewData(
        text = context.getString(co.yml.coreui.feature.ytag.R.string.tag_border),
        tagViewModifiers = tagViewModifiers
    )
}

@Composable
fun shadowTagData(
    context: Context,
    backgroundColor: Color,
    textColor: Color,
): TagViewData {
    val tagViewModifiers = TagViewModifiers.Builder().width(100.dp)
        .textColor(textColor)
        .backgroundColor(backgroundColor)
        .shape(CircleShape).shadowElevation(dimensionResource(id = R.dimen.padding_tiny))
        .style(textStyle).maxLines(1).overFlow(TextOverflow.Ellipsis).alphaAnimation(
            AlphaAnimation(enabled = true)
        ).build()

    return TagViewData(
        text = stringResource(id = co.yml.coreui.feature.ytag.R.string.tag_shadow),
        tagViewModifiers = tagViewModifiers
    )
}
