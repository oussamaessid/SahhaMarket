package app.sahhamarket.presentation.cart.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.CartResponnse
import app.sahhamarket.domain.usecase.cart.GetCartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemUseCase: GetCartItemUseCase,
    private val cartEventBus: CartEventBus

) : ViewModel() {

    private val _stateCart = MutableStateFlow(CartUiState())
    val stateCart: StateFlow<CartUiState> = _stateCart.asStateFlow()

    init {
        observeCartUpdates()
        processAction(UiAction.LoadCart)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                UiAction.LoadCart -> loadCartData()
                UiAction.ClearCart -> clearCart()
            }
        }
    }

    private suspend fun loadCartData() {
        _stateCart.update { it.copy(isLoading = true) }

        val result = getCartItemUseCase()

        _stateCart.update {
            it.copy(
                isLoading = false,
                cart = result.getOrNull(),
                hasError = result.isFailure
            )
        }
    }

    private fun observeCartUpdates() {
        viewModelScope.launch {
            cartEventBus.cartEvents.collect {
                processAction(UiAction.LoadCart)
            }
        }
    }

    private fun clearCart() {
        _stateCart.update { it.copy(cart = null) }
    }

    sealed interface UiAction {
        object LoadCart : UiAction
        object ClearCart : UiAction
    }

    data class CartUiState(
        val isLoading: Boolean = false,
        val cart: CartResponnse? = null,
        val hasError: Boolean = false
    )
}
