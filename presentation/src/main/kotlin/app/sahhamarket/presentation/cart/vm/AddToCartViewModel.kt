package app.sahhamarket.presentation.cart.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Item
import app.sahhamarket.domain.usecase.cart.AddToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddToCartViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val cartEventBus: CartEventBus


) : ViewModel() {

    private val _uiState = MutableStateFlow<AddCartState>(AddCartState.Idle)
    val uiState: StateFlow<AddCartState> = _uiState

    fun addItemToCart(item: Item) {
        viewModelScope.launch {
            _uiState.value = AddCartState.Loading
            try {
                addToCartUseCase(item)
                cartEventBus.emitCartUpdateEvent()
                _uiState.value = AddCartState.Success
            } catch (e: Exception) {
                _uiState.value = AddCartState.Failure(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = AddCartState.Idle
    }
}

sealed class AddCartState {
    object Idle : AddCartState()
    object Loading : AddCartState()
    object Success : AddCartState()
    data class Failure(val message: String) : AddCartState()
}
