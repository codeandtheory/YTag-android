package co.yml.coreui.feature.ytag.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.yml.coreui.core.ui.templates.AppBarWithBackButton
import co.yml.coreui.core.ui.theme.CoreUICatalogTheme
import co.yml.coreui.core.ui.ystepper.StepperView
import co.yml.coreui.core.ui.ystepper.model.StepperIcon
import co.yml.coreui.core.ui.ystepper.model.StepperModifiers
import co.yml.coreui.ui.R
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class YStepperActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoreUICatalogTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = CoreUICatalogTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_y_stepper),
                            onBackPressed = {
                                onBackPressed()
                            })
                    }) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(it)
                            .padding(PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_normal)))
                    ) {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal_medium)))

                        DefaultStepper()

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_normal)))

                        CustomStepper()
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultStepper() {
    val minValue = 1
    val maxValue = 10

    var count by remember {
        mutableStateOf(minValue)
    }

    var enableLeadingIcon by remember {
        mutableStateOf(true)
    }

    var enableTrailingIcon by remember {
        mutableStateOf(true)
    }

    var showDeleteIcon by remember {
        mutableStateOf(true)
    }

    var stepperVisibility  by remember {
        mutableStateOf(true)
    }

    enableLeadingIcon = count > minValue
    showDeleteIcon = count <= minValue
    enableTrailingIcon = count < maxValue


    Log.i("check_min_value", "data: false")

    val stepperModifiers = StepperModifiers.Builder()
        .width(140.dp)
        .height(36.dp)
        .enableBorder(true)
        .borderColor(Color.Gray)
        .borderWidth(1.dp)
        .text(count.toString())
        .textColor(Color.Black)
        .shape(CircleShape)
        .showDeleteIcon(showDeleteIcon)
        .leadingIcon(
            StepperIcon(
                enable = enableLeadingIcon,
                icon = R.drawable.ic_remove_20px,
                iconTint = Color.Black,
                onClickListener = {
                    count -= 1
                },
            )
        )

        .trailingIcon(
            StepperIcon(
                enable = enableTrailingIcon,
                icon = R.drawable.ic_add_20px,
                iconTint = Color.Black,
                onClickListener = {
                    count += 1
                })
        )
        .deleteIcon(
            StepperIcon(
                icon = R.drawable.ic_delete_20px,
                iconTint = Color.Black,
                onClickListener = {
                    stepperVisibility = false
                }
            )
        )
        .build()

    StepperView(
        visible = stepperVisibility,
        stepperModifier = stepperModifiers
    )
}

@Composable
fun CustomStepper() {
    val minValue = 1
    val maxValue = 10

    var count by remember {
        mutableStateOf(minValue)
    }

    var enableLeadingIcon by remember {
        mutableStateOf(true)
    }

    var enableTrailingIcon by remember {
        mutableStateOf(true)
    }

    var showDeleteIcon by remember {
        mutableStateOf(true)
    }

    var stepperVisibility  by remember {
        mutableStateOf(true)
    }

    enableLeadingIcon = count > minValue
    showDeleteIcon = count <= minValue
    enableTrailingIcon = count < maxValue

    val stepperModifiers = StepperModifiers.Builder()
        .width(140.dp)
        .height(36.dp)
        .backgroundColor(Color.Yellow)
        .text(count.toString())
        .textColor(Color.Black)
        .shape(CircleShape)
        .showDeleteIcon(showDeleteIcon)
        .build()

    StepperView(
        visible = stepperVisibility,
        stepperModifier = stepperModifiers,
        textView = {
            Text(text = "$count")
        },
        leadingIcon = {
            IconButton(enabled = enableLeadingIcon, onClick = {
                count -= 2
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove_20px),
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            IconButton(enabled = enableTrailingIcon, onClick = {
                count += 2
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_20px),
                    contentDescription = null
                )
            }
        },
        deleteIcon = {
            IconButton(enabled = enableTrailingIcon, onClick = {
                stepperVisibility = false
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete_20px),
                    contentDescription = null
                )
            }
        }
    )
}
