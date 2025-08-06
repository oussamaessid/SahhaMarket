package app.sahhamarket.presentation.location.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Address
import app.sahhamarket.domain.usecase.location.AddNewAddressUseCase
import app.sahhamarket.domain.usecase.location.GetAddressFromLatLngUseCase
import app.sahhamarket.domain.usecase.location.GetDevicePositionUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewAddressViewModel @Inject constructor(
    private val getDevicePositionUseCase: GetDevicePositionUseCase,
    private val getAddressFromLatLngUseCase: GetAddressFromLatLngUseCase,
    private val addNewAddressUseCase: AddNewAddressUseCase,
) : ViewModel() {

    private val _stateAddNewAddress: MutableStateFlow<UiState> =
        MutableStateFlow(UiState())
    val stateAddNewAddress: StateFlow<UiState> = _stateAddNewAddress.asStateFlow()
    private var newAddress = mutableStateOf<Address?>(null)

    init {
        processAction(UiAction.InitData)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.InitData -> initData()
                is UiAction.OnUpdateAddress -> {
                    _stateAddNewAddress.update {
                        it.copy(
                            latLng = action.position,
                            address = getAddressFromLatLngUseCase(position = action.position)?.let { address: Address ->
                                newAddress.value = address.copy(name = it.name)
                                address.toString()
                            } ?: "Error when trying to recover the address"
                        )
                    }
                }
                is UiAction.OnUpdateAddressName -> {
                    _stateAddNewAddress.update {
                        it.copy(name = action.name)
                    }
                    newAddress.value?.let { address ->
                        newAddress.value = address.copy(name = action.name)
                    }
                }
                is UiAction.OnAddNewAddress -> {
                    newAddress.value?.let { address ->
                        addNewAddressUseCase(address.copy(isDefault = true))
                    }
                }
            }
        }
    }

    private suspend fun initData() {
        _stateAddNewAddress.value = _stateAddNewAddress.value.copy(isLoading = true)
        getDevicePositionUseCase().fold(
            onSuccess = { location ->
                _stateAddNewAddress.update {
                    it.copy(
                        isLoading = false,
                        latLng = location,
                        address = getAddressFromLatLngUseCase(position = location)?.let { address: Address ->
                            newAddress.value = address.copy(name = it.name)
                            address.toString()
                        } ?: "Error when trying to recover the address"
                    )
                }
            },
            onFailure = { error ->
                _stateAddNewAddress.update {
                    it.copy(
                        isLoading = false,
                        address = error.message ?: "The position cannot be regained."
                    )
                }
            }
        )
    }

    sealed interface UiAction {
        data object InitData : UiAction
        data class OnUpdateAddress(val position: LatLng) : UiAction
        data class OnUpdateAddressName(val name: String) : UiAction
        data object OnAddNewAddress : UiAction
    }

    data class UiState(
        val isLoading: Boolean = true,
        val latLng: LatLng? = null,
        val name: String = "",
        val address: String = "",
        val error: String? = null
    )
}