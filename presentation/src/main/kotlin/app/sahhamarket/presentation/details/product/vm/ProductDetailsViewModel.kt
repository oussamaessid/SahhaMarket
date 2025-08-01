package app.sahhamarket.presentation.details.product.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.ProductDetails
import app.sahhamarket.domain.usecase.products.AddProductToCartUseCase
import app.sahhamarket.domain.usecase.products.DecrementProductQuantityUseCase
import app.sahhamarket.domain.usecase.products.GetProductUseCase
import app.sahhamarket.domain.usecase.products.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val decrementProductQuantityUseCase: DecrementProductQuantityUseCase,
) : ViewModel() {

    private val _stateProduct: MutableStateFlow<ProductUiState> =
        MutableStateFlow(ProductUiState.Loading)
    val stateProduct: StateFlow<ProductUiState> = _stateProduct.asStateFlow()

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.InitData -> initData(action.productId)
                is UiAction.AddProduct -> addProductOrQuantityToCart(action.product)
                is UiAction.LessQuantityProduct -> decrementProductQuantity(action.product)
            }
        }
    }

    private suspend fun initData(productId: Long) {
        getProductUseCase(productId = productId)
            .onStart { _stateProduct.update { ProductUiState.Loading } }
            .catch { _stateProduct.update { ProductUiState.Error } }
            .collectLatest { result ->
                result.fold(
                    onSuccess = { product -> _stateProduct.update { ProductUiState.Success(product) } },
                    onFailure = { _stateProduct.update { ProductUiState.Error } }
                )
            }
    }

    private suspend fun addProductOrQuantityToCart(product: Product) {
        addProductToCartUseCase(product)
    }

    private suspend fun decrementProductQuantity(product: Product) {
        decrementProductQuantityUseCase(product)
    }

    sealed interface UiAction {
        data class InitData(val productId: Long) : UiAction
        data class AddProduct(val product: Product) : UiAction
        data class LessQuantityProduct(val product: Product) : UiAction
    }

    sealed interface ProductUiState {
        data object Loading : ProductUiState
        data object Error : ProductUiState
        data class Success(val productDetails: ProductDetails) : ProductUiState
    }
}