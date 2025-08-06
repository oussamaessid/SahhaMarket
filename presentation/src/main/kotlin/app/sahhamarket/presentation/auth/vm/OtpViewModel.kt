package app.sahhamarket.presentation.auth.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.usecase.auth.RequestOtpUseCase
import app.sahhamarket.domain.usecase.auth.ValidateOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OtpViewModel @Inject constructor(
    private val validateOtpUseCase: ValidateOtpUseCase,
    private val requestOtpUseCase: RequestOtpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun setPhoneNumber(phone: String) {
        _uiState.update { it.copy(phoneNumber = phone) }
        Log.d("new phone " , _uiState.value.phoneNumber)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.OnCheckOtp -> {
                    checkOtp(
                        otp = action.otp,
                        onSuccess = { _uiEvent.emit(UiEvent.OnSuccess) },
                        onFailure = { _uiEvent.emit(UiEvent.OnError(it)) }
                    )
                }
                is UiAction.OnResendOtp -> {
                    Log.d("OtpViewModel", "Calling resendOtp")
                    resendOtp(
                        onSuccess = { _uiEvent.emit(UiEvent.OnOtpResent) },
                        onFailure = { _uiEvent.emit(UiEvent.OnError(it)) }
                    )
                }
            }
        }
    }
    fun requestOtp(
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        _uiState.update { it.copy(phoneNumber = phone, isLoading = true) }
        viewModelScope.launch {
            requestOtpUseCase(phone).fold(
                onSuccess = {

                    _uiState.update { it.copy(isLoading = false) }
                    onSuccess()
                },
                onFailure = {
                    _uiState.update { it.copy(isLoading = false) }
                    onFailure(it.message ?: "Unknown error")
                }
            )
        }
    }

    suspend fun checkOtp(
        otp: String,
        onSuccess: suspend () -> Unit,
        onFailure: suspend (String) -> Unit
    ) {
        Log.d("checkotp", "checkOtp called with otp=$otp")
        val phone = _uiState.value.phoneNumber
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            Log.d("bde",phone)
            validateOtpUseCase(phone, otp).fold(
                onSuccess = {
                    Log.d("OTP Validated", it)
                    onSuccess()
                },
                onFailure = {
                    Log.d("OTP Failed", it.message ?: "Unknown error")
                    onFailure("OTP validation failed")
                }
            )

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    suspend fun resendOtp(
        onSuccess: suspend () -> Unit,
        onFailure: suspend (String) -> Unit
    ) {
        val phone = _uiState.value.phoneNumber
        if (phone.isBlank()) {
            onFailure("Phone number is missing")
            return
        }

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            requestOtpUseCase(phone).fold(
                onSuccess = {
                    Log.d("✅ Resend", "OTP resent successfully")
                    _uiState.update { it.copy(isLoading = false) }
                    onSuccess()
                },
                onFailure = {
                    Log.d("❌ Resend", it.message ?: "Unknown error")
                    _uiState.update { it.copy(isLoading = false) }
                    onFailure("Failed to resend OTP")
                }
            )
        }
    }

    sealed interface UiAction {
        data class OnCheckOtp(val otp: String) : UiAction
        data object OnResendOtp : UiAction
    }

    data class UiState(
        val isLoading: Boolean = false,
        val phoneNumber: String = ""
    )

    sealed class UiEvent {
        data object OnSuccess : UiEvent()
        data object OtpSent : UiEvent()
        data class OnError(val message: String) : UiEvent()
        data object OnOtpResent : UiEvent()
    }
}
