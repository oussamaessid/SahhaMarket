package app.sahhamarket.presentation.option.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.OptionInfo
import app.sahhamarket.resources.R
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign

@Composable
fun OptionScreen(
    goToOtpScreen: () -> Unit,
) {
    OptionContent(
        goToOtpScreen = goToOtpScreen
    )
}

@Composable
fun OptionContent(
    goToOtpScreen: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    var isPrivacyPolicyChecked by remember { mutableStateOf(false) }
    val options = listOf(
        OptionInfo(image = R.drawable.ic_bulk, title = R.string.txt_bulk),
        OptionInfo(image = R.drawable.ic_details, title = R.string.txt_details)
    )
    val selectedOption = remember { mutableStateOf(options[0]) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_phone),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .height(250.dp)
                .padding(bottom = MaterialTheme.spacing.xxs, end = MaterialTheme.spacing.xxs)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = MaterialTheme.spacing.s,
                    vertical = MaterialTheme.spacing.xxl
                )
        ) {

            OptionsRadioButton(
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.m,
                    horizontal = MaterialTheme.spacing.m
                ),
                options = options,
                selectedOption = selectedOption.value,
                onOptionSelected = { selectedOption.value = it }
            )

            TextField(
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedTextColor = Black,
                    unfocusedTextColor = Black,
                    focusedPlaceholderColor = Black.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = Black.copy(alpha = 0.6f)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_phone),
                        tint = FountainBlue,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.txt_hint_phone_number),
                        textAlign = TextAlign.Center
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = MaterialTheme.spacing.unit,
                        color = Black,
                        shape = MaterialTheme.shapes.small
                    )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.l))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isPrivacyPolicyChecked = !isPrivacyPolicyChecked
                    }
                    .padding(horizontal = MaterialTheme.spacing.s)
            ) {
                Checkbox(
                    checked = isPrivacyPolicyChecked,
                    onCheckedChange = { isPrivacyPolicyChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = FountainBlue,
                        uncheckedColor = FountainBlue,
                        checkmarkColor = White
                    )
                )

                Text(
                    text = stringResource(R.string.txt_privacy_policy),
                    style = MaterialTheme.typography.bodyLarge,
                    color = FountainBlue,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.xs)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer(alpha = 0.8f)
                    .padding(
                        horizontal = MaterialTheme.spacing.l,
                        vertical = MaterialTheme.spacing.s
                    ),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = FountainBlue,
                    contentColor = White
                ),
                onClick = {
                    goToOtpScreen()
                }
            ) {
                Text(
                    text = stringResource(R.string.txt_btn_next),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.xs)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun OptionsRadioButton(
    options: List<OptionInfo>,
    selectedOption: OptionInfo,
    onOptionSelected: (OptionInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    var optionSelected by remember { mutableStateOf(selectedOption) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
        modifier = modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        optionSelected = option
                        onOptionSelected(option)
                    }
                    .padding(all = MaterialTheme.spacing.s)
            ) {
                Image(
                    painter = painterResource(
                        id = when {
                            optionSelected == option && option.title == R.string.txt_details -> R.drawable.ic_selecte_details
                            optionSelected == option && option.title == R.string.txt_bulk -> R.drawable.ic_selecte_bulk
                            option.title == R.string.txt_details -> R.drawable.ic_details
                            else -> R.drawable.ic_bulk
                        }
                    ),
                    contentDescription = null,
                    colorFilter = if (optionSelected != option) ColorFilter.colorMatrix(ColorMatrix().apply {
                        setToSaturation(0f)
                    }) else null,
                    modifier = Modifier.size(80.dp)
                )

                Text(
                    text = stringResource(option.title),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.xs)

                )
            }
        }
    }
}