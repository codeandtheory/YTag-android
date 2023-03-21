package co.yml.coreui.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.coreui.ui.theme.YCoreUITheme


@Composable
fun CoreUIComponents(title: String, onClick: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp)
                .fillMaxWidth()
                .height(50.dp), onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = YCoreUITheme.colors.button)
        ) {
            Text(
                text = title,
                style = YCoreUITheme.typography.button,
                color = YCoreUITheme.colors.text
            )
        }
    }
}
