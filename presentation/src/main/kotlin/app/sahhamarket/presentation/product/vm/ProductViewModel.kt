package app.sahhamarket.presentation.product.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.usecase.products.GetProductsUseCase
import app.sahhamarket.presentation.home.model.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _stateProducts: MutableStateFlow<ProductUiState> =
        MutableStateFlow(ProductUiState())
    val stateProducts: StateFlow<ProductUiState> = _stateProducts.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _stateProducts.update { it.copy(products = ProductUiModel.Loading) }
            _stateProducts.update { state ->
                state.copy(
                    products = getProductsUseCase().fold(
                        onSuccess = { ProductUiModel.Success(it) },
                        onFailure = { ProductUiModel.Error }
                    )
                )
            }
        }
    }

    data class ProductUiState(
        val products: ProductUiModel = ProductUiModel.Loading
    )
}