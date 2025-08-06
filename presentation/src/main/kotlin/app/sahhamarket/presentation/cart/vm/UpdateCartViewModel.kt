package app.sahhamarket.presentation.cart.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Item
import app.sahhamarket.domain.usecase.cart.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UpdateCartItemViewModel @Inject constructor(
    private val updateCartItemUseCase: UpdateCartUseCase,
    private val cartEventBus: CartEventBus
) : ViewModel() {

    private val _uiState = MutableStateFlow<UpdateCartState>(UpdateCartState.Idle)
    val uiState: StateFlow<UpdateCartState> = _uiState

    fun updateItem(item: Item) {
        viewModelScope.launch {
            _uiState.value = UpdateCartState.Loading
            try {
                updateCartItemUseCase(item)
                cartEventBus.emitCartUpdateEvent()
                _uiState.value = UpdateCartState.Success
            } catch (e: Exception) {
                _uiState.value = UpdateCartState.Failure(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = UpdateCartState.Idle
    }
}

sealed class UpdateCartState {
    object Idle : UpdateCartState()
    object Loading : UpdateCartState()
    object Success : UpdateCartState()
    data class Failure(val message: String) : UpdateCartState()
}
