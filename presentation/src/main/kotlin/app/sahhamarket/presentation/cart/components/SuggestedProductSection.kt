package app.sahhamarket.presentation.cart.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.sahhamarket.domain.model.Product
import app.sahhamarket.presentation.home.components.PopularDealsContent

@Composable
public fun ProductSection(
    title: String,
    products: List<Product>,
    isVisible: Boolean
) {
    Column {


        AnimatedVisibility(visible = isVisible) {
            Column {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = title, style = MaterialTheme.typography.titleMedium)
                }
                LazyRow {
                    items(products) { product ->
                        ProductItem(product)
                    }
                }
            }
        }
    }
}
