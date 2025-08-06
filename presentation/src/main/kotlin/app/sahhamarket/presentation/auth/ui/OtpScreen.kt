package app.sahhamarket.presentation.auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.ChateauGreen
import app.sahhamarket.compose.theme.CoralRed
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.compose.theme.transparent
import app.sahhamarket.presentation.auth.vm.OtpViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OtpScreen(
    phone: String,
    onOtpVerified: () -> Unit,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is OtpViewModel.UiEvent.OnSuccess -> {
                    onOtpVerified()
                    keyboardController?.hide()

                }
                is OtpViewModel.UiEvent.OnError -> {
                    showError = true
                }

                OtpViewModel.UiEvent.OnOtpResent -> TODO()
                OtpViewModel.UiEvent.OtpSent -> TODO()
            }
        }
    }

    OtpContent(
        phoneNumber = phone,
        onVerifyClick = {
                otpCode ->
            viewModel.setPhoneNumber(phone)
            viewModel.processAction(OtpViewModel.UiAction.OnCheckOtp(otpCode))
        },
        onResendClick = {
            viewModel.processAction(OtpViewModel.UiAction.OnResendOtp)
        },
        onBackClick = {

        },
        showError = showError
    )

}

@Composable
fun OtpContent(
    phoneNumber: String = "+966 45 ******06",
    onVerifyClick: (String) -> Unit = {},
    onResendClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    showError : Boolean

) {
    var otp by remember { mutableStateOf(List(6) { "" }) }
    val focusRequesters = List(6) { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.m)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.s))

        Text(
            text = "OTP Verification",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))
        val maskedPhone = if (phoneNumber.length >= 6) {
            val first = phoneNumber.take(4)
            val last = phoneNumber.takeLast(2)
            val stars = "*".repeat(phoneNumber.length - 6)
            "$first$stars$last"
        } else {
            phoneNumber
        }

        val text = "Enter the verification code we just sent to your number $maskedPhone"
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Black60
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.l))

        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            otp.forEachIndexed { index, digit ->
                OutlinedTextField(
                    value = digit,
                    onValueChange = {
                        if (it.length <= 1 && it.all(Char::isDigit)) {
                            otp = otp.toMutableList().also { list -> list[index] = it }

                            if (it.isNotEmpty() && index < 5) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .width(MaterialTheme.spacing.xl)
                        .height(MaterialTheme.spacing.xxl)
                        .focusRequester(focusRequesters[index])
                        .onKeyEvent { event ->
                            if (event.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DEL &&
                                 index > 0 && index !=5
                                ) {
                                focusRequesters[index - 1].requestFocus()
                                otp = otp.toMutableList().also { list -> list[index - 1] = "" }
                                true
                            }else if (event.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DEL && index == 5 ){
                                focusRequesters[4].requestFocus()
                               true
                            }
                            else {
                                false
                            }
                        },
                    textStyle = MaterialTheme.typography.headlineLarge,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = if (showError) CoralRed else Black60,
                        focusedIndicatorColor = if (showError) CoralRed else ChateauGreen,
                        focusedContainerColor = transparent,
                        unfocusedContainerColor = transparent,
                        cursorColor = ChateauGreen
                    )
                )

            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.s))
        if (showError){
            Text(text = "Wrong Code , please try again" , color = CoralRed , modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center)
            Spacer(Modifier.height(MaterialTheme.spacing.m))
        }
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

            Text(text = "Didn't receive code?")
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xxxs))
            Text(
                text = "Resend",
                color = ChateauGreen,
                modifier = Modifier.clickable(onClick = onResendClick),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xl))

        Button(
            onClick = {
                onVerifyClick(otp.joinToString(""))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.xl),
            enabled = otp.joinToString("").isNotEmpty(),
            colors = ButtonColors(
                contentColor = ChateauGreen,
                containerColor = ChateauGreen,
                disabledContentColor = Black60,
                disabledContainerColor = Black60
            ),
            shape = RoundedCornerShape(MaterialTheme.spacing.xs)
        ) {
            Text("Verify" , color = White, style = MaterialTheme.typography.titleSmall)
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}
