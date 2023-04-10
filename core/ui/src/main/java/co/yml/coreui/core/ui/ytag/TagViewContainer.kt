package co.yml.coreui.core.ui.ytag

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import co.yml.coreui.core.ui.ytag.model.TagViewContainerModifiers
import co.yml.coreui.core.ui.ytag.model.TagViewData
import co.yml.coreui.core.ui.ytag.model.TagViewModifiers

/**
 * [TagViewContainer] compose method used for hosting multiple chips
 *
 * @param tagViewData Defines the list of tag view data
 * @param tagViewContainerModifiers collection of modifier elements that decorate or add behavior to TagView elements
 */
@Composable
fun TagViewContainer(
    tagViewData: List<TagViewData>,
    tagViewContainerModifiers: TagViewContainerModifiers
) {
    //add more tag into the list
    val updatedTagViewData = tagViewData.toMutableList()
    val moreTag = tagViewContainerModifiers.moreTagConfiguration
    updatedTagViewData.add(moreTag)

    with(tagViewContainerModifiers) {
        val modifier = Modifier
        val context = LocalContext.current
        Box(
            modifier = modifier
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
                .width(width = width ?: Dp.Unspecified)
                .height(height = height)
                .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
                .clickable { }
                .semantics {
                    this.contentDescription =
                        context.getString(co.yml.coreui.ui.R.string.tag_view_container_accessibility_title)
                }
                .testTag("tag_view_container")
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .padding(containerPaddingValues)
        ) {


            TagViewContainerLayout(
                tagViewContainerModifiers = tagViewContainerModifiers,
                content = {
                    updatedTagViewData.forEach {
                        with(it) {
                            val containerItemClick = {
                                tagViewContainerModifiers.onClick.invoke(it)
                            }
                            TagView(
                                text = text,
                                leadingIcon = leadingIcon,
                                trailingIcon = trailingIcon,
                                enabled = enabled,
                                tagViewModifiers = tagViewModifiers,
                                overFlowText = "",
                                onClick = containerItemClick
                            )
                        }
                    }
                })
        }
    }
}

/**
 * [TagViewContainerLayout] used for creating a custom layout to hosting y tag
 * @param tagViewContainerModifiers collection of modifier elements that decorate or add behavior to tag view container
 * @param content content of the container [Tag views]
 */
@Composable
fun TagViewContainerLayout(
    tagViewContainerModifiers: TagViewContainerModifiers,
    content: @Composable () -> Unit
) {
    Layout(content = content) { measurables, constraints ->
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )

        var currentRow = 0
        var currentOffset = IntOffset.Zero

        val placeAbles = measurables.map { measurable ->
            val placeAble: Placeable = measurable.measure(looseConstraints)

            if (currentOffset.x > 0f && currentOffset.x + placeAble.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                    .toInt() > constraints.maxWidth
            ) {
                Log.i(
                    "check_measure",
                    "c.XOffset: ${currentOffset.x} p.Width: ${placeAble.width} p.Height: ${placeAble.height} tagHSpace: ${
                        tagViewContainerModifiers.tagSpacingHorizontal.toPx().toInt()
                    } tagVSpace: ${
                        tagViewContainerModifiers.tagSpacingVertical.toPx().toInt()
                    } max w: ${constraints.maxWidth}"
                )
                currentRow += 1
                currentOffset =
                    currentOffset.copy(
                        x = 0,
                        y = currentOffset.y + placeAble.height + tagViewContainerModifiers.tagSpacingVertical.toPx()
                            .toInt()
                    )
            }
            Log.i("check_measure", "common: ${currentOffset}")

            placeAble to currentOffset.also {
                currentOffset = it.copy(
                    x = it.x + placeAble.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                        .toInt()
                )
            }
        }

        layout(width = constraints.maxWidth,
            height = placeAbles.lastOrNull()?.run { first.height } ?: 0) {
            Log.i("check_placeAble", "size: ${placeAbles.size}")
            placeAbles.forEachIndexed { index, placeable ->
                val (placeable, offset) = placeable
                //check whether current item has enough space
                val currentItemHorizontalSpace =
                    placeable.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                        .toInt()
                val currentItemVerticalSpace =
                    placeable.height + tagViewContainerModifiers.tagSpacingVertical.toPx()
                        .toInt()

                if (offset.x + currentItemHorizontalSpace < constraints.maxWidth && offset.y + currentItemVerticalSpace < constraints.maxHeight) {
                    //current item has space
                    Log.i("check_placeable", "index: $index current item has space")
                    val nextItemIndex = index + 1
                    if (nextItemIndex <= placeAbles.lastIndex) {
                        val nextItemOffset = placeAbles[nextItemIndex].second

                        val nextItemHorizontalSpace =
                            placeAbles[nextItemIndex].first.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                                .toInt()
                        val nextItemVerticalSpace =
                            placeAbles[nextItemIndex].first.height + tagViewContainerModifiers.tagSpacingVertical.toPx()
                                .toInt()

                        if (nextItemOffset.x + nextItemHorizontalSpace < constraints.maxWidth && nextItemOffset.y + nextItemVerticalSpace < constraints.maxHeight) {
                            //next item has space
                            Log.i(
                                "check_placeable",
                                "next index: $nextItemIndex nextItemOffset : $nextItemOffset next item has space and placed"
                            )
                            placeable.place(offset.x, offset.y)
                        } else {
                            //next item has no space
                            //check space to accommodate more tag
                            Log.i("check_placeable", "index: $index next item has no space")
                            val moreTagPlaceAble = placeAbles.last()
                            val remainingTags = placeAbles.lastIndex - 1 - index


//                            tagViewContainerModifiers.moreTagConfiguration.overFlowText.invoke(remainingTags)
                            val moreTagHorizontalSpace =
                                moreTagPlaceAble.first.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                                    .toInt()
                            val moreTagVerticalSpace =
                                moreTagPlaceAble.first.height + tagViewContainerModifiers.tagSpacingVertical.toPx()
                                    .toInt()

                            if (offset.x + moreTagHorizontalSpace < constraints.maxWidth && offset.y + moreTagVerticalSpace < constraints.maxHeight) {
                                //place more tag
                                Log.i("check_placeable", "index: $index more tag placed")
                                moreTagPlaceAble.first.place(offset.x, offset.y)
                                return@layout
                            } else {
                                Log.i("check_placeable", "index: $index more tag has no space")
                            }
                        }
                    }
                } else {
                    //current item has no space
                    //check space to accommodate more tag
                    Log.i("check_placeable", "index: $index current item has no space")
                    val remainingTags = placeAbles.lastIndex - 1 - index
                    val moreTagPlaceAble = placeAbles.last()
                    val moreTagHorizontalSpace =
                        moreTagPlaceAble.first.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                            .toInt()
                    val moreTagVerticalSpace =
                        moreTagPlaceAble.first.height + tagViewContainerModifiers.tagSpacingVertical.toPx()
                            .toInt()

                    if (offset.x + moreTagHorizontalSpace < constraints.maxWidth && offset.y + moreTagVerticalSpace < constraints.maxHeight) {
                        //place more tag
                        Log.i("check_placeable", "index: $index more tag placed")
                        placeable.place(offset.x, offset.y)
                        return@layout
                    } else {
                        Log.i("check_placeable", "index: $index more tag has no space")
                    }
                }
            }
        }
    }
}

@Preview(name = "Default Tag container")
@Composable
fun DefaultTagContainer() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .build()
    val tagViewData = listOf(
        TagViewData(text = "Tag 1", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 2", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 3", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 4", tagViewModifiers = tagViewModifiers)
    )

    val tagViewContainerModifiers = TagViewContainerModifiers.Builder()
        .shape(RoundedCornerShape(10.dp))
        .width(200.dp)
        .height(120.dp)
        .build()

    TagViewContainer(
        tagViewData = tagViewData,
        tagViewContainerModifiers = tagViewContainerModifiers
    )
}

@Preview(name = "Tag container with border")
@Composable
fun BorderTagContainer() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .build()
    val tagViewData = listOf(
        TagViewData(text = "Tag 1", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 2", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 3", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 4", tagViewModifiers = tagViewModifiers)
    )

    val tagViewContainerModifiers = TagViewContainerModifiers.Builder()
        .shape(RoundedCornerShape(10.dp))
        .width(200.dp)
        .height(120.dp)
        .enableBorder(true)
        .borderColor(Color.Red)
        .borderWidth(1.dp)
        .build()

    TagViewContainer(
        tagViewData = tagViewData,
        tagViewContainerModifiers = tagViewContainerModifiers
    )
}

@Preview(name = "Tag container with background")
@Composable
fun BackgroundTagContainer() {
    val tagViewModifiers = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .build()
    val tagViewData = listOf(
        TagViewData(text = "Tag 1", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 2", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 3", tagViewModifiers = tagViewModifiers),
        TagViewData(text = "Tag 4", tagViewModifiers = tagViewModifiers)
    )

    val tagViewContainerModifiers = TagViewContainerModifiers.Builder()
        .shape(RoundedCornerShape(10.dp))
        .backgroundColor(Color.Gray)
        .width(200.dp)
        .height(120.dp)
        .build()

    TagViewContainer(
        tagViewData = tagViewData,
        tagViewContainerModifiers = tagViewContainerModifiers
    )
}
