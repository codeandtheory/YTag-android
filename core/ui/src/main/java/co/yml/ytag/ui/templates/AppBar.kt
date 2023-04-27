@file:OptIn(ExperimentalMaterial3Api::class)

package co.yml.ytag.ui.templates

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ytag.ui.R
import co.yml.ytag.ui.theme.YTagTheme

/**
 * Top app bars display information at the top of a screen.
 */
@Composable
fun AppBar() {
    Surface(shadowElevation = dimensionResource(id = R.dimen.padding_small)) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = YTagTheme.colors.button),
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    color = YTagTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = YTagTheme.typography.subHeader
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
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = YTagTheme.colors.button),
            title = {
                Text(
                    text = title,
                    color = YTagTheme.colors.text,
                    textAlign = TextAlign.Center,
                    style = YTagTheme.typography.subHeader
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
                        tint = YTagTheme.colors.primary
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YTagTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = YTagTheme.colors.background,
            topBar = { AppBar() },
            content = { Box(Modifier.padding(it)) {} }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarWithBackButton() {
    YTagTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = YTagTheme.colors.background,
            topBar = {
                AppBarWithBackButton(title = stringResource(id = R.string.title_y_tag)) {
                }
            },
            content = { Box(Modifier.padding(it)) {} }
        )
    }
}
