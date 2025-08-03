package app.sahhamarket.presentation.product.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.usecase.products.AddProductToCartUseCase
import app.sahhamarket.domain.usecase.products.DecrementProductQuantityUseCase
import app.sahhamarket.domain.usecase.products.GetProductsUseCase
import app.sahhamarket.presentation.home.vm.HomeViewModel.UiAction
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
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val decrementProductQuantityUseCase: DecrementProductQuantityUseCase,
) : ViewModel() {

    private val _stateProducts: MutableStateFlow<ProductUiState> =
        MutableStateFlow(ProductUiState.Loading)
    val stateProducts: StateFlow<ProductUiState> = _stateProducts.asStateFlow()

    init {
        processAction(UiAction.InitData)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.InitData -> initData()
                is UiAction.OnAddToCart -> addProductToCart(action.product)
                is UiAction.OnLessQuantityProduct -> decrementProductQuantity(action.product)
            }
        }
    }

    private suspend fun initData() {
        getProductsUseCase()
            .onStart { _stateProducts.update { ProductUiState.Loading } }
            .catch { _stateProducts.update { ProductUiState.Error } }
            .collectLatest { result ->
                result.fold(
                    onSuccess = { products ->
                        _stateProducts.update {
                            ProductUiState.Success(
                                products
                            )
                        }
                    },
                    onFailure = { _stateProducts.update { ProductUiState.Error } }
                )
            }
    }

    private suspend fun addProductToCart(product: Product) {
        addProductToCartUseCase(product)
    }

    private suspend fun decrementProductQuantity(product: Product) {
        decrementProductQuantityUseCase(product)
    }

    sealed interface UiAction {
        data object InitData : UiAction
        data class OnAddToCart(val product: Product) : UiAction
        data class OnLessQuantityProduct(val product: Product) : UiAction
    }

    sealed interface ProductUiState {
        data object Loading : ProductUiState
        data object Error : ProductUiState
        data class Success(val products: List<Product> = emptyList()) : ProductUiState
    }
}