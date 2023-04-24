package co.yml.coreui.core.ui.ystepper.model

import androidx.compose.ui.graphics.Color
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme

class StepperIcon(
    val icon: Int,
    val iconTint: Color,
    val onClickListener: () -> Unit = {},
    val enable: Boolean = true,
    val semantics: String? =null
)
