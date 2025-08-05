package app.sahhamarket.presentation.product.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.presentation.product.components.ProductContent
import app.sahhamarket.presentation.product.vm.ProductsViewModel
import app.sahhamarket.presentation.product.vm.ProductsViewModel.ProductUiState
import app.sahhamarket.resources.R

@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = hiltViewModel(),
) {
    val state by viewModel.stateProducts.collectAsStateWithLifecycle()

    ProductsContent(
        state = state,
        onAddToCart = { viewModel.processAction(ProductsViewModel.UiAction.OnAddToCart(it)) },
        onLessQuantity = {
            viewModel.processAction(
                ProductsViewModel.UiAction.OnLessQuantityProduct(
                    it
                )
            )
        }
    )
}

@Composable
fun ProductsContent(
    state: ProductUiState,
    onAddToCart: (Product) -> Unit,
    onLessQuantity: (Product) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        when (state) {
            is ProductUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is ProductUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ms),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ms),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = MaterialTheme.spacing.s)
                ) {
                    items(state.products) { product ->
                        ProductContent(
                            product = product,
                            modifier = Modifier,
                            onAddToCart = { onAddToCart(product) },
                            onAddQuantity = { onAddToCart(product) },
                            onLessQuantity = { onLessQuantity(product) }
                        )
                    }
                }
            }

            is ProductUiState.Error -> {
                Text(
                    text = stringResource(R.string.txt_error_loading_products),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}