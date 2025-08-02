package app.sahhamarket.presentation.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.presentation.auth.vm.OtpViewModel
import app.sahhamarket.resources.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OtpScreen(
    viewModel: OtpViewModel = hiltViewModel(),
    goToLocationScreen: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is OtpViewModel.UiEvent.OnSuccess -> {
                    goToLocationScreen()
                    keyboardController?.hide()
                }
                is OtpViewModel.UiEvent.OnError -> {
                    //TODO Showing snackBar error message and clear textField
                }
            }
        }
    }

    OtpContent(
        state = state,
        onCheckOtp = {
            viewModel.processAction(OtpViewModel.UiAction.OnCheckOtp(it))
        }
    )
}

@Composable
fun OtpContent(
    state: OtpViewModel.UiState,
    onCheckOtp: (String) -> Unit,
) {
    var otpValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MaterialTheme.spacing.s)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        Image(
            painter = painterResource(id = R.drawable.img_otp),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter OTP",
                color = FountainBlue,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.s)
            )

            OutlinedTextField(
                value = otpValue,
                onValueChange = { newOtp ->
                    otpValue = newOtp
                    if (newOtp.length == 6) {
                        onCheckOtp(newOtp)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = MaterialTheme.shapes.small,
                textStyle = MaterialTheme.typography.titleLarge
            )
        }
    }
}
