package co.yml.coreui.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import co.yml.coreui.R
import co.yml.coreui.ui.compositions.AppBarWithBackButton
import co.yml.coreui.ui.theme.YCoreUITheme
import co.yml.coreui.ui.ytag.model.TagViewModifiers
import co.yml.ycoreui.ui.ytag.TagView

@ExperimentalMaterial3Api
class YTagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YCoreUITheme {
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
                        LazyColumn(content = {
                            items(4) { item ->
                                when (item) {
                                    0 -> DefaultTag()
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultTag(){
    val tagViewModifiers = TagViewModifiers.Builder()
        .text("Default")
        .build()

    TagView(tagViewModifiers = tagViewModifiers)
}
