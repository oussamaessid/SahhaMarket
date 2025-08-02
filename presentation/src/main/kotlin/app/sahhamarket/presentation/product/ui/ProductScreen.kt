package app.sahhamarket.presentation.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.presentation.home.model.ProductUiModel
import app.sahhamarket.presentation.product.components.ProductContent
import app.sahhamarket.presentation.product.vm.ProductViewModel
import app.sahhamarket.resources.R

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val productState = viewModel.stateProducts.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.s)
    ) {
        when (val productsState = productState.value.products) {
            is ProductUiModel.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is ProductUiModel.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(MaterialTheme.spacing.s),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(productsState.products) { product ->
                        ProductContent(
                            product = product,
                            modifier = Modifier
                        )
                    }
                }
            }
            is ProductUiModel.Error -> {
                Text(
                    text = stringResource(R.string.txt_error_loading_products),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}