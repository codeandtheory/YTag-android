@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package co.yml.coreui.core.ui.templates

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme
import co.yml.coreui.ui.R

/**
 * Top app bars display information at the top of a screen.
 */
@Composable
fun AppBar() {
    Surface(shadowElevation = dimensionResource(id = R.dimen.padding_small)) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = CoreUICatalogTheme.colors.button),
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    color = CoreUICatalogTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = CoreUICatalogTheme.typography.subHeader
                )
            }
        )
    }
}

/**
 * Top app bars display information and actions at the top of a screen.
 *
 * @param title title of the screen
 * @param onBackPressed implementation of back press event
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithBackButton(title: String, onBackPressed: () -> Unit) {
    Surface(shadowElevation = dimensionResource(id = R.dimen.padding_small)) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = CoreUICatalogTheme.colors.button),
            title = {
                Text(
                    text = title,
                    color = CoreUICatalogTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = CoreUICatalogTheme.typography.subHeader
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { onBackPressed() },
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back",
                        tint = CoreUICatalogTheme.colors.primary
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
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = CoreUICatalogTheme.colors.background,
            topBar = { AppBar() },
            content = { Box(Modifier.padding(it)) {} }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarWithBackButton() {
    CoreUICatalogTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = CoreUICatalogTheme.colors.background,
            topBar = {
                AppBarWithBackButton(title = stringResource(id = R.string.title_y_tag)) {
                }
            },
            content = { Box(Modifier.padding(it)) {} }
        )
    }
}
