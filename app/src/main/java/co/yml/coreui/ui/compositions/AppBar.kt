@file:OptIn(ExperimentalMaterial3Api::class)

package co.yml.coreui.ui.compositions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.coreui.R
import co.yml.coreui.ui.theme.CoreUICatalogTheme

@Composable
fun AppBar() {
    Surface(shadowElevation = 6.dp) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = CoreUICatalogTheme.colors.button),
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    color = CoreUICatalogTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = CoreUICatalogTheme.typography.header
                )
            }
        )
    }
}

@Composable
fun AppBarWithBackButton(title: String, onBackPressed: () -> Unit) {
    Surface(shadowElevation = 6.dp) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = CoreUICatalogTheme.colors.button),
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    color = CoreUICatalogTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = CoreUICatalogTheme.typography.header
                )
            },
            actions = {
                IconButton(
                    onClick = { onBackPressed() },
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back",
                        tint = Color.Unspecified
                    )
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoreUICatalogTheme {
        AppBar()
    }
}


@Preview(showBackground = true)
@Composable
fun AppBarWithBackButton() {
    CoreUICatalogTheme {
        AppBarWithBackButton(title = stringResource(id = R.string.title_y_tag)) {
        }
    }
}
