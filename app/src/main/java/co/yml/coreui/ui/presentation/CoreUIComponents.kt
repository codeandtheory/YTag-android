package co.yml.coreui.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme
import co.yml.coreui.ui.R

@Composable
fun CoreUIComponents(title: String, onClick: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal_medium)))
        Button(
            modifier = Modifier
                .padding(
                    end = dimensionResource(id = R.dimen.padding_normal_medium),
                    start = dimensionResource(
                        id = R.dimen.padding_normal_medium
                    )
                )
                .fillMaxWidth()
                .height(48.dp), onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = CoreUICatalogTheme.colors.button)
        ) {
            Text(
                text = title,
                style = CoreUICatalogTheme.typography.button,
                color = CoreUICatalogTheme.colors.text
            )
        }
    }
}
