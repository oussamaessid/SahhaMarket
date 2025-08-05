package app.sahhamarket.presentation.home.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.Receipt
import app.sahhamarket.presentation.home.components.CategoryContent
import app.sahhamarket.presentation.home.components.DailyReceiptContent
import app.sahhamarket.presentation.home.components.HeaderSectionContent
import app.sahhamarket.presentation.home.components.HorizontalPagerContent
import app.sahhamarket.presentation.home.components.InviteFriendsContent
import app.sahhamarket.presentation.home.components.LocationContent
import app.sahhamarket.presentation.home.components.SearchBarContent
import app.sahhamarket.presentation.home.model.CategoryUiModel
import app.sahhamarket.presentation.home.model.PopularDealsUiModel
import app.sahhamarket.presentation.home.model.PromoCardUiModel
import app.sahhamarket.presentation.home.model.ReceiptUiModel
import app.sahhamarket.presentation.home.vm.HomeViewModel
import app.sahhamarket.presentation.product.components.ProductContent
import app.sahhamarket.resources.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    goToRecipesDetailScreen: () -> Unit,
    goToProductDetailScreen: (Long) -> Unit,
    goToCategoryScreen: () -> Unit,
    goToProductsScreen: () -> Unit,
    goToSubcategoryScreen: (Long) -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.stateHome.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HomeViewModel.UiEvent.AddProductToCart -> {
                    Toast.makeText(context, context.getString(event.message), Toast.LENGTH_LONG)
                        .show()
                }

                is HomeViewModel.UiEvent.NavigateToCategoryScreen -> {
                    goToCategoryScreen()
                }

                is HomeViewModel.UiEvent.NavigateToProductDetail -> {
                    goToProductDetailScreen(event.productId)
                }

                is HomeViewModel.UiEvent.NavigateToRecipeDetail -> {
                    goToRecipesDetailScreen()
                }

                is HomeViewModel.UiEvent.NavigateToSubcategoryScreen -> {
                    goToSubcategoryScreen(event.categoryId)
                }
            }
        }
    }

    HomeContent(
        state = state,
        onAddProductToCart = { viewModel.processAction(HomeViewModel.UiAction.AddProduct(it)) },
        onAddQuantity = { viewModel.processAction(HomeViewModel.UiAction.AddProduct(it)) },
        onLessQuantity = { viewModel.processAction(HomeViewModel.UiAction.LessQuantityProduct(it)) },
        onCategoryClicked = { categoryId ->
            viewModel.processAction(HomeViewModel.UiAction.NavigateToSubcategoryScreen(categoryId))
        },
        onProductClicked = { productId ->
            viewModel.processAction(HomeViewModel.UiAction.NavigateToProductDetail(productId))
        },
        onRecipeClicked = {
            viewModel.processAction(HomeViewModel.UiAction.NavigateToRecipeDetail)
        },
        onNavigateToCategoryScreen = goToCategoryScreen,
        onNavigateToProductsScreen = goToProductsScreen,
        onAddToCart = { viewModel.processAction(HomeViewModel.UiAction.OnAddToCart(it)) },
        onLessQuantityReceipt = {
            viewModel.processAction(
                HomeViewModel.UiAction.OnLessQuantityReceipt(it)
            )
        }
    )
}

@Composable
fun HomeContent(
    state: HomeViewModel.HomeUiState,
    onAddProductToCart: (Product) -> Unit,
    onAddQuantity: (Product) -> Unit,
    onLessQuantity: (Product) -> Unit,
    onCategoryClicked: (Long) -> Unit,
    onAddToCart: (Receipt) -> Unit,
    onLessQuantityReceipt: (Receipt) -> Unit,
    onProductClicked: (Long) -> Unit,
    onRecipeClicked: () -> Unit,
    onNavigateToCategoryScreen: () -> Unit,
    onNavigateToProductsScreen: () -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        LazyColumn {
            if (state.currentAddress != null) {
                item {
                    LocationContent(
                        address = state.currentAddress,
                        onLocationClicked = {}
                    )
                }
            }
            item {
                SearchBarContent(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.s),
                    onSearch = {}
                )
            }

            item {
                HeaderSectionContent(
                    title = stringResource(R.string.txt_header_categories),
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.s),
                    onSeeAllClicked = onNavigateToCategoryScreen
                )

                when (val data = state.categories) {
                    is CategoryUiModel.Loading -> {}
                    is CategoryUiModel.Error -> {}
                    is CategoryUiModel.Success -> {
                        LazyRow(
                            contentPadding = PaddingValues(MaterialTheme.spacing.s),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(data.categories) { category ->
                                CategoryContent(
                                    category = category,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            onCategoryClicked(category.id)
                                        }
                                )
                            }
                        }
                    }
                }
            }
            item {
                when (val data = state.promoList) {
                    is PromoCardUiModel.Loading -> {}
                    is PromoCardUiModel.Error -> {}
                    is PromoCardUiModel.Success -> {
                        HorizontalPagerContent(
                            modifier = Modifier.padding(vertical = MaterialTheme.spacing.s),
                            pageList = data.promoList
                        )
                    }
                }
            }

            item {
                HeaderSectionContent(
                    title = stringResource(R.string.txt_header_popular_deals),
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.s),
                    onSeeAllClicked = onNavigateToProductsScreen
                )
            }

            item {
                when (val data = state.popularDeals) {
                    is PopularDealsUiModel.Loading -> {}
                    is PopularDealsUiModel.Error -> {}
                    is PopularDealsUiModel.Success -> {
                        val products = data.popularDeals.take(4)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = White)
                                .padding(horizontal = MaterialTheme.spacing.s, vertical = MaterialTheme.spacing.s)
                        ) {
                            products.chunked(2).forEach { rowItems ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s)
                                ) {
                                    rowItems.forEach { product ->
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                        ) {
                                            ProductContent(
                                                product = product,
                                                onAddToCart = { onAddProductToCart(product) },
                                                onAddQuantity = { onAddQuantity(product) },
                                                onLessQuantity = { onLessQuantity(product) }
                                            )
                                        }
                                    }
                                    if (rowItems.size < 2) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                InviteFriendsContent(
                    modifier = Modifier
                        .background(color = White)
                        .padding(horizontal = MaterialTheme.spacing.s),
                    onInviteClicked = {}
                )
            }

            item {
                HeaderSectionContent(
                    title = stringResource(R.string.txt_header_daily_receipt),
                    modifier = Modifier
                        .background(color = White)
                        .padding(vertical = MaterialTheme.spacing.s)
                )
            }
            when (val data = state.recipes) {
                is ReceiptUiModel.Loading -> {}
                is ReceiptUiModel.Error -> {}
                is ReceiptUiModel.Success -> {
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(MaterialTheme.spacing.s),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = White),
                        ) {
                            items(data.recipes) { receipt ->
                                DailyReceiptContent(
                                    receipt = receipt,
                                    modifier = Modifier,
                                    onAddToCart = { onAddToCart(receipt) },
                                    onAddQuantity = { onAddToCart(receipt) },
                                    onLessQuantity = { onLessQuantityReceipt(receipt) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}