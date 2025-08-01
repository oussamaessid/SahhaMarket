package app.sahhamarket.presentation.home.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.presentation.home.components.CategoryContent
import app.sahhamarket.presentation.home.components.DailyReceiptContent
import app.sahhamarket.presentation.home.components.HeaderSectionContent
import app.sahhamarket.presentation.home.components.HorizontalPagerContent
import app.sahhamarket.presentation.home.components.InviteFriendsContent
import app.sahhamarket.presentation.home.components.LocationContent
import app.sahhamarket.presentation.home.components.PopularDealsContent
import app.sahhamarket.presentation.home.components.ProductContent
import app.sahhamarket.presentation.home.components.SearchBarContent
import app.sahhamarket.presentation.home.components.SubCategoryContent
import app.sahhamarket.presentation.home.model.CategoryUiModel
import app.sahhamarket.presentation.home.model.PopularDealsUiModel
import app.sahhamarket.presentation.home.model.ProductUiModel
import app.sahhamarket.presentation.home.model.PromoCardUiModel
import app.sahhamarket.presentation.home.model.ReceiptUiModel
import app.sahhamarket.presentation.home.model.SubCategoryUiModel
import app.sahhamarket.presentation.home.vm.HomeViewModel
import app.sahhamarket.resources.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    goToRecipesDetailScreen: (String) -> Unit,
    goToProductDetailScreen: (Long) -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.stateHome.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HomeViewModel.UiEvent.AddProductToCart -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
                is HomeViewModel.UiEvent.NavigateToProductDetail -> {
                    goToProductDetailScreen(event.productId)
                }
                is HomeViewModel.UiEvent.NavigateToRecipeDetail -> {
                    goToRecipesDetailScreen(event.recipeId)
                }
            }
        }
    }

    HomeContent(
        state = state,
        onAddProductToCart = { viewModel.processAction(HomeViewModel.UiAction.AddProduct(it)) },
        onAddQuantity = { viewModel.processAction(HomeViewModel.UiAction.AddProduct(it)) },
        onLessQuantity = { viewModel.processAction(HomeViewModel.UiAction.LessQuantityProduct(it)) },
        onProductClicked = { productId ->
            viewModel.processAction(HomeViewModel.UiAction.NavigateToProductDetail(productId))
        },
        onRecipeClicked = { recipeId ->
            viewModel.processAction(HomeViewModel.UiAction.NavigateToRecipeDetail(recipeId))
        }
    )
}

@Composable
fun HomeContent(
    state: HomeViewModel.HomeUiState,
    onAddProductToCart: (Product) -> Unit,
    onAddQuantity: (Product) -> Unit,
    onLessQuantity: (Product) -> Unit,
    onProductClicked: (Long) -> Unit,
    onRecipeClicked: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            if (state.currentAddress != null) {
                item {
                    LocationContent(
                        address = state.currentAddress,
                        modifier = Modifier.background(color = White),
                        onLocationClicked = {}
                    )
                }
            }
            item {
                SearchBarContent(
                    modifier = Modifier
                        .background(color = White)
                        .padding(vertical = MaterialTheme.spacing.s),
                    onSearch = {}
                )
            }
            item {
                when (val data = state.promoList) {
                    is PromoCardUiModel.Loading -> {}
                    is PromoCardUiModel.Error -> {}
                    is PromoCardUiModel.Success -> {
                        HorizontalPagerContent(
                            modifier = Modifier,
                            pageList = data.promoList
                        )
                    }
                }
            }
            item {
                HeaderSectionContent(
                    title = stringResource(R.string.txt_header_categories),
                    modifier = Modifier
                        .background(color = White)
                        .padding(vertical = MaterialTheme.spacing.s)
                )
            }
            item {
                var selectedIndex by remember { mutableIntStateOf(0) }
                when (val data = state.categories) {
                    is CategoryUiModel.Loading -> {}
                    is CategoryUiModel.Error -> {}
                    is CategoryUiModel.Success -> {
                        LazyRow(
                            contentPadding = PaddingValues(MaterialTheme.spacing.s),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = White)
                        ) {
                            itemsIndexed(data.categories) { index, category ->
                                CategoryContent(
                                    category = category,
                                    isSelected = index == selectedIndex,
                                    modifier = Modifier.clickable {
                                        selectedIndex = index
                                    }
                                )
                            }
                        }
                    }
                }
            }
            item {
                var selectedIndex by remember { mutableIntStateOf(0) }
                when (val data = state.subCategories) {
                    is SubCategoryUiModel.Loading -> {}
                    is SubCategoryUiModel.Error -> {}
                    is SubCategoryUiModel.Success -> {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.s),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = White),
                        ) {
                            itemsIndexed(data.subCategories) { index, subCategory ->
                                SubCategoryContent(
                                    subCategory = subCategory,
                                    isSelected = index == selectedIndex,
                                    modifier = Modifier.clickable {
                                        selectedIndex = index
                                    }
                                )
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }
            item {
                when (val data = state.products) {
                    is ProductUiModel.Loading -> {}
                    is ProductUiModel.Error -> {}
                    is ProductUiModel.Success -> {
                        LazyHorizontalGrid(
                            rows = GridCells.Fixed(2),
                            contentPadding = PaddingValues(all = MaterialTheme.spacing.s),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(512.dp)
                                .background(color = White)
                        ) {
                            items(data.products.size) { index ->
                                val product = data.products[index]
                                ProductContent(
                                    product = product,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            onProductClicked(product.id)
                                        }
                                )
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
                    title = stringResource(R.string.txt_header_popular_deals),
                    modifier = Modifier
                        .background(color = White)
                        .padding(vertical = MaterialTheme.spacing.s)
                )
            }
            item {
                when (val data = state.popularDeals) {
                    is PopularDealsUiModel.Loading -> {}
                    is PopularDealsUiModel.Error -> {}
                    is PopularDealsUiModel.Success -> {
                        LazyRow(
                            contentPadding = PaddingValues(MaterialTheme.spacing.s),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = White),
                        ) {
                            items(data.popularDeals) { product ->
                                PopularDealsContent(
                                    product = product,
                                    modifier = Modifier,
                                    onAddProductToCart = { onAddProductToCart(product) },
                                    onAddQuantity = { onAddQuantity(product) },
                                    onLessQuantity = { onLessQuantity(product) },
                                )
                            }
                        }
                    }
                }
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
                                    modifier = Modifier.clickable {
                                        onRecipeClicked(receipt.id.toString())
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}