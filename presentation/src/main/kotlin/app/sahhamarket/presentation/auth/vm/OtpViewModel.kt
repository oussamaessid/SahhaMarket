package app.sahhamarket.presentation.auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(false))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.OnCheckOtp -> onCheckOtp(action.otp)
            }
        }
    }

    private suspend fun onCheckOtp(otp: String) {
        _uiState.update { it.copy(isLoading = true) }
        validateOtpUseCase(otp).fold(
            onSuccess = {
                _uiEvent.emit(UiEvent.OnSuccess)
                _uiState.update { it.copy(isLoading = false) }
            },
            onFailure = {
                _uiEvent.emit(UiEvent.OnError(message = "Error"))
                _uiState.update { it.copy(isLoading = false) }
            }
        )
    }

    sealed interface UiAction {
        data class OnCheckOtp(val otp: String) : UiAction
    }

    data class UiState(
        val isLoading: Boolean = false,
    )

    sealed class UiEvent {
        data object OnSuccess : UiEvent()
        data class OnError(val message: String) : UiEvent()
    }
}