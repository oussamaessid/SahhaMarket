package app.sahhamarket.presentation.location.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Address
import app.sahhamarket.domain.usecase.location.GetAllAddressListUseCase
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
class LocationViewModel @Inject constructor(
    private val getAllAddressListUseCase: GetAllAddressListUseCase,
) : ViewModel() {

    private val _stateLocation: MutableStateFlow<UiState> =
        MutableStateFlow(UiState())
    val stateLocation: StateFlow<UiState> = _stateLocation.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        processAction(UiAction.InitData)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.InitData -> initData()
                is UiAction.OnCheckLocationPermission -> {
                    if (action.isGranted) {
                        _uiEvent.emit(UiEvent.GoToAddNewAddressScreen)
                    } else {
                        _uiEvent.emit(UiEvent.ShowPermissionDialog)
                    }
                }
            }
        }
    }

    private suspend fun initData() {
        getAllAddressListUseCase().collect { result ->
            val default = result.firstOrNull { address -> address.isDefault }
            _stateLocation.update {
                it.copy(
                    deliveryLocationList = result,
                    defaultAddress = default?.toString()
                )
            }
        }
    }

    sealed interface UiAction {
        data object InitData : UiAction
        data class OnCheckLocationPermission(val isGranted: Boolean) : UiAction
    }

    sealed class UiEvent {
        data object GoToAddNewAddressScreen : UiEvent()
        data object ShowPermissionDialog : UiEvent()
        data object GoToHomeScreen : UiEvent()
    }

    data class UiState(
        val deliveryLocationList: List<Address> = emptyList(),
        val defaultAddress: String? = null,
    )
}
