package co.yml.coreui.core.ui.ytag

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
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
    //add overflow details tag into the list
    val updatedTagViewData = tagViewData.toMutableList()
    val moreTag = tagViewContainerModifiers.moreTagConfiguration
    updatedTagViewData.add(moreTag)

    with(tagViewContainerModifiers) {
        val context = LocalContext.current
        var modifier = if (tagViewContainerModifiers.width != Dp.Unspecified) {
            Modifier.width(tagViewContainerModifiers.width)
        } else {
            Modifier.wrapContentWidth()
        }

        modifier = if (tagViewContainerModifiers.height != Dp.Unspecified) {
            modifier.then(Modifier.height(tagViewContainerModifiers.height))
        } else {
            modifier.then(Modifier.wrapContentHeight())
        }

        modifier = modifier.then(Modifier
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
            .defaultMinSize(minWidth, minHeight)
            .clickable { }
            .semantics {
                this.contentDescription =
                    tagViewContainerModifiers.semantics.ifEmpty { context.getString(co.yml.coreui.ui.R.string.tag_view_container_accessibility_title) }
            }
            .testTag("tag_view_container")
            .background(
                color = backgroundColor,
                shape = shape
            )
            .padding(containerPaddingValues)
        )

        Box(
            modifier = modifier
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
    val localDensity = LocalDensity.current

    Layout(content = content) { measurables, constraints ->
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )

        var currentRow = 0
        var currentOffset = IntOffset.Zero

        //Measurement phase
        val placeAbles = measurables.map { measurable ->
            val placeAble: Placeable = measurable.measure(looseConstraints)

            //calculate the offsets to place the tags in layout phase
            if (currentOffset.x > 0f && currentOffset.x + placeAble.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                    .toInt() > constraints.maxWidth
            ) {
                currentRow += 1
                currentOffset =
                    currentOffset.copy(
                        x = 0,
                        y = currentOffset.y + placeAble.height + tagViewContainerModifiers.tagSpacingVertical.toPx()
                            .toInt()
                    )
            }
            placeAble to currentOffset.also {
                currentOffset = it.copy(
                    x = it.x + placeAble.width + tagViewContainerModifiers.tagSpacingHorizontal.toPx()
                        .toInt()
                )
            }
        }

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            placeAbles.forEachIndexed { index, tagPlaceable ->
                val (placeable, offset) = tagPlaceable
                //check whether container  has enough space to place the current tag
                if (offset.x + placeable.width < constraints.maxWidth && offset.y + placeable.height < constraints.maxHeight) {
                    //space available for current tag
                    val nextItemIndex = index + 1
                    //check whether container  has enough space to place the next tag
                    if (nextItemIndex <= placeAbles.lastIndex) {
                        val nextItemOffset = placeAbles[nextItemIndex].second
                        if (nextItemOffset.x + placeAbles[nextItemIndex].first.width < constraints.maxWidth && nextItemOffset.y + placeAbles[nextItemIndex].first.height < constraints.maxHeight) {
                            //space available for next tag
                            placeable.place(offset.x, offset.y)
                        } else {
                            //space not available for next tag
                            //place the over flow tag
                            val overflow = showOverFlow(
                                index,
                                placeAbles,
                                tagViewContainerModifiers,
                                constraints,
                                localDensity
                            )
                            overflow?.let {
                                it.first.place(it.second)
                            }
                            return@layout
                        }
                    }
                } else {
                    //space available for current tag
                    //place the over flow tag
                    val overflow = showOverFlow(
                        index,
                        placeAbles,
                        tagViewContainerModifiers,
                        constraints,
                        localDensity
                    )
                    overflow?.let {
                        it.first.place(it.second)
                    }
                    return@layout
                }
            }
        }
    }
}

/**
 * Used for displaying the over flow details when tags not fit in the container
 * @param index current placeAble index
 * @param placeAbles placeAble details of tags
 * @param tagViewContainerModifiers  collection of modifier elements that decorate or add behavior to tag view container
 * @param constraints immutable constraints for measuring layouts
 * @param localDensity A density of the screen. Used for the conversions between pixels and Dp
 */
fun showOverFlow(
    index: Int,
    placeAbles: List<Pair<Placeable, IntOffset>>,
    tagViewContainerModifiers: TagViewContainerModifiers,
    constraints: Constraints,
    localDensity: Density
): Pair<Placeable, IntOffset>? {
    val offset = placeAbles[index].second
    val placeable = placeAbles[index]

    if (tagViewContainerModifiers.moreTagConfiguration.showOverFlow) {
        val moreTagPlaceAble = placeAbles.last()

        if (offset.x + moreTagPlaceAble.first.width < constraints.maxWidth && offset.y + moreTagPlaceAble.first.height < constraints.maxHeight) {
            //place more tag
            //check whether space available for over flow tag to place in between current [which replace over flow tag] and previous tags
            val previousIndex = index - 1
            if (previousIndex >= 0) {
                val previousOffset = placeAbles[previousIndex].second
                val previousTag = placeAbles[previousIndex].first

                val moreTagXOffset =
                    previousOffset.x + localDensity.run { tagViewContainerModifiers.tagSpacingHorizontal.toPx() }
                        .toInt() + previousTag.width
                val moreTagYOffset = previousOffset.y

                if (moreTagXOffset + moreTagPlaceAble.first.width < constraints.maxWidth && moreTagYOffset + moreTagPlaceAble.first.height < constraints.maxHeight) {
                    val remainingTags = placeAbles.lastIndex - index
                    tagViewContainerModifiers.moreTagConfiguration.overFlowText.invoke(remainingTags)
                    return Pair(moreTagPlaceAble.first, IntOffset(moreTagXOffset, moreTagYOffset))
                }
            }
            val remainingTags = placeAbles.lastIndex - index
            tagViewContainerModifiers.moreTagConfiguration.overFlowText.invoke(remainingTags)
            return moreTagPlaceAble
        }
    } else {
        return placeable
    }

    return null
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
