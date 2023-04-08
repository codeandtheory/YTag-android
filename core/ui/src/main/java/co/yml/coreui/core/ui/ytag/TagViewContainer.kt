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
import androidx.compose.ui.platform.testTag
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
    val updatedTagViewData = tagViewData.toMutableList()
    val moreTagModifier = TagViewModifiers.Builder()
        .shape(CircleShape)
        .backgroundColor(Color.Black)
        .textColor(Color.White)
        .build()

    updatedTagViewData.add(TagViewData("More", moreTagModifier))

    with(tagViewContainerModifiers) {
        val modifier = Modifier
        Box(
            modifier = modifier
                .width(width = width ?: Dp.Unspecified)
                .height(height = height)
                .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
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
                .clickable { }
                .testTag("tag_view_container")
                .padding(containerPaddingValues)
                .background(
                    color = backgroundColor,
                    shape = shape
                )
        ) {
            TagViewContainerLayout(
                tagViewContainerModifiers = tagViewContainerModifiers,
                content = {
                    updatedTagViewData.forEach {
                        with(it) {
                            TagView(
                                text = text,
                                leadingIcon = leadingIcon,
                                trailingIcon = trailingIcon,
                                enabled = enabled,
                                tagViewModifiers = tagViewModifiers
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

        //todo sree_ check whether the padding is correct

        val placeAbles = measurables.map { measurable ->
            val placeAble = measurable.measure(looseConstraints)

            //todo sree_ is horizontal and vertical space [surface] required
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
        .build()

    TagViewContainer(
        tagViewData = tagViewData,
        tagViewContainerModifiers = tagViewContainerModifiers
    )
}
