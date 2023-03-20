@file:OptIn(ExperimentalFoundationApi::class)

package co.yml.coreui.feature.post.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.ui.templates.FullWidthDeletableCard
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme
import co.yml.coreui.core.ui.theme.dimensions
import co.yml.coreui.feature.post.R

/**
 * Post list item
 *
 * @param postItem
 * @param modifier
 * @param onDeleted
 * @receiver
 */
@Composable
fun PostListItem(postItem: Post, modifier: Modifier = Modifier, onDeleted: (Post) -> Unit) {
    FullWidthDeletableCard(modifier, {
        onDeleted(postItem)
    }) {
        Column(
            modifier = Modifier
                .padding(dimensions.paddingMedium)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.title),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(dimensions.horizontalSpacer))
                Text(text = postItem.title, style = MaterialTheme.typography.bodyMedium)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.details),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.padding(dimensions.horizontalSpacer))
                Text(text = postItem.body, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

/**
 * Preview home list detail screen
 *
 */
@Preview("Post List Item", device = Devices.PIXEL_C)
@Preview(
    "Post List Item (dark)",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_C
)
@Preview("Post List Item (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewHomeListDetailScreen() {
    CoreUICatalogTheme {
        PostListItem(Post(0, 0, "title1", "Body")) {
        }
    }
}
