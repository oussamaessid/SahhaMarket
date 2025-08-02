package app.sahhamarket.presentation.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Address
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.usecase.categories.GetCategoriesUseCase
import app.sahhamarket.domain.usecase.location.GetDefaultAddressUseCase
import app.sahhamarket.domain.usecase.populardeals.GetPopularDealsUseCase
import app.sahhamarket.domain.usecase.products.AddProductToCartUseCase
import app.sahhamarket.domain.usecase.products.DecrementProductQuantityUseCase
import app.sahhamarket.domain.usecase.products.GetProductsUseCase
import app.sahhamarket.domain.usecase.promocard.GetPromoCardsUseCase
import app.sahhamarket.domain.usecase.recipes.GetRecipesUseCase
import app.sahhamarket.domain.usecase.subCategories.GetSubCategoriesUseCase
import app.sahhamarket.presentation.home.model.CategoryUiModel
import app.sahhamarket.presentation.home.model.PopularDealsUiModel
import app.sahhamarket.presentation.home.model.ProductUiModel
import app.sahhamarket.presentation.home.model.PromoCardUiModel
import app.sahhamarket.presentation.home.model.ReceiptUiModel
import app.sahhamarket.presentation.home.model.SubCategoryUiModel
import app.sahhamarket.resources.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDefaultAddressUseCase: GetDefaultAddressUseCase,
    private val getPromoCardsUseCase: GetPromoCardsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getPopularDealsUseCase: GetPopularDealsUseCase,
    private val getRecipesUseCase: GetRecipesUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val decrementProductQuantityUseCase: DecrementProductQuantityUseCase,
) : ViewModel() {

    private val _stateHome: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState())
    val stateHome: StateFlow<HomeUiState> = _stateHome.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        processAction(UiAction.InitData)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.InitData -> initData()
                is UiAction.AddProduct -> addProductOrQuantityToCart(action.product)
                is UiAction.LessQuantityProduct -> decrementProductQuantity(action.product)
                is UiAction.NavigateToCategoryScreen -> {
                    _uiEvent.emit(UiEvent.NavigateToCategoryScreen(action.categoryId))
                }
                is UiAction.NavigateToProductDetail -> {
                    _uiEvent.emit(UiEvent.NavigateToProductDetail(action.productId))
                }
                is UiAction.NavigateToRecipeDetail -> {
                    _uiEvent.emit(UiEvent.NavigateToRecipeDetail(action.recipeId))
                }
                is UiAction.NavigateToSubcategoryScreen -> {
                    _uiEvent.emit(UiEvent.NavigateToSubcategoryScreen(action.categoryId))
                }
            }
        }
    }

    private suspend fun initData() {
        coroutineScope {
            launch { getDefaultAddress() }
            launch { getPromoCards() }
            launch { getCategories() }
            launch { getSubCategories() }
            launch { getProducts() }
            launch { getPopularDeals() }
            launch { getRecipes() }
        }
    }

    private suspend fun getDefaultAddress() {
        _stateHome.update { state ->
            state.copy(
                currentAddress = getDefaultAddressUseCase()
            )
        }
    }

    private suspend fun getPromoCards() {
        _stateHome.update { it.copy(promoList = PromoCardUiModel.Loading) }
        _stateHome.update { state ->
            state.copy(
                promoList = getPromoCardsUseCase().fold(
                    onSuccess = { PromoCardUiModel.Success(it) },
                    onFailure = { PromoCardUiModel.Error }
                )
            )
        }
    }

    private suspend fun getCategories() {
        _stateHome.update { it.copy(categories = CategoryUiModel.Loading) }
        _stateHome.update { state ->
            state.copy(
                categories = getCategoriesUseCase().fold(
                    onSuccess = { CategoryUiModel.Success(it) },
                    onFailure = { CategoryUiModel.Error }
                )
            )
        }
    }

    private suspend fun getSubCategories() {
        _stateHome.update { it.copy(subCategories = SubCategoryUiModel.Loading) }
        _stateHome.update { state ->
            state.copy(
                subCategories = getSubCategoriesUseCase(1L).fold(
                    onSuccess = { SubCategoryUiModel.Success(it) },
                    onFailure = { SubCategoryUiModel.Error }
                )
            )
        }
    }

    private suspend fun getProducts() {
        _stateHome.update { it.copy(products = ProductUiModel.Loading) }
        _stateHome.update { state ->
            state.copy(
                products = getProductsUseCase().fold(
                    onSuccess = { ProductUiModel.Success(it) },
                    onFailure = { ProductUiModel.Error }
                )
            )
        }
    }

    private suspend fun getPopularDeals() {
        getPopularDealsUseCase()
            .onStart { _stateHome.update { it.copy(popularDeals = PopularDealsUiModel.Loading) } }
            .catch { _stateHome.update { it.copy(popularDeals = PopularDealsUiModel.Error) } }
            .collectLatest { result ->
                _stateHome.update { state ->
                    state.copy(
                        popularDeals = result.fold(
                            onSuccess = { PopularDealsUiModel.Success(it) },
                            onFailure = { PopularDealsUiModel.Error }
                        )
                    )
                }
            }
    }

    private suspend fun getRecipes() {
        _stateHome.update { it.copy(recipes = ReceiptUiModel.Loading) }
        _stateHome.update { state ->
            state.copy(
                recipes = getRecipesUseCase().fold(
                    onSuccess = { ReceiptUiModel.Success(it) },
                    onFailure = { ReceiptUiModel.Error }
                )
            )
        }
    }

    private suspend fun addProductOrQuantityToCart(product: Product) {
        addProductToCartUseCase(product).fold(
            onSuccess = {
                _uiEvent.emit(UiEvent.AddProductToCart(R.string.txt_add_product_success))
            },
            onFailure = {
                _uiEvent.emit(UiEvent.AddProductToCart(R.string.txt_add_product_failure))
            }
        )
    }

    private suspend fun decrementProductQuantity(product: Product) {
        decrementProductQuantityUseCase(product).fold(
            onSuccess = {
                _uiEvent.emit(UiEvent.AddProductToCart(R.string.txt_add_product_success))
            },
            onFailure = {
                _uiEvent.emit(UiEvent.AddProductToCart(R.string.txt_add_product_failure))
            }
        )
    }

    sealed interface UiAction {
        data object InitData : UiAction
        data class AddProduct(val product: Product) : UiAction
        data class LessQuantityProduct(val product: Product) : UiAction
        data class NavigateToCategoryScreen(val categoryId: Long) : UiAction
        data class NavigateToProductDetail(val productId: Long) : UiAction
        data class NavigateToRecipeDetail(val recipeId: String) : UiAction
        data class NavigateToSubcategoryScreen(val categoryId: Long) : UiAction
    }

    sealed interface UiEvent {
        data class AddProductToCart(val message: Int) : UiEvent
        data class NavigateToCategoryScreen(val categoryId: Long) : UiEvent
        data class NavigateToProductDetail(val productId: Long) : UiEvent
        data class NavigateToRecipeDetail(val recipeId: String) : UiEvent
        data class NavigateToSubcategoryScreen(val categoryId: Long) : UiEvent
    }

    data class HomeUiState(
        val currentAddress: Address? = null,
        val currentLanguage: String = "En", //TODO update when create languageUseCase
        val promoList: PromoCardUiModel = PromoCardUiModel.Loading,
        val categories: CategoryUiModel = CategoryUiModel.Loading,
        val subCategories: SubCategoryUiModel = SubCategoryUiModel.Loading,
        val products: ProductUiModel = ProductUiModel.Loading,
        val popularDeals: PopularDealsUiModel = PopularDealsUiModel.Loading,
        val recipes: ReceiptUiModel = ReceiptUiModel.Loading,
    )
}