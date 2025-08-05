package app.sahhamarket.presentation.details.product.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.CaribbeanGreen
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.WildSand
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.resources.R

@Composable
fun SimilarProductsSection(
    products: List<Product>,
    onSeeAllClick: () -> Unit,
    onProductClick: (Product) -> Unit,
    onAddToCartClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = MaterialTheme.spacing

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.txt_header_similar_products),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(id = R.string.txt_see_all),
                style = MaterialTheme.typography.bodyMedium,
                color = CaribbeanGreen,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(spacing.s))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(spacing.ms)
        ) {
            items(products) { product ->
                SimilarProductCard(
                    product = product,
                    onClick = { onProductClick(product) },
                    onAddClick = { onAddToCartClick(product) }
                )
            }
        }
    }
}

@Composable
fun SimilarProductCard(
    product: Product,
    onClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = MaterialTheme.spacing

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(120.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(WildSand, RoundedCornerShape(spacing.xs)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = product.imageUrl.toIntOrNull() ?: R.drawable.pomodorini),
                contentDescription = product.title,
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Fit
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(spacing.xxxs)
                    .size(24.dp)
                    .background(White, CircleShape)
                    .clickable { onAddClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.txt_btn_add_to_cart),
                    modifier = Modifier.size(MaterialTheme.spacing.s),
                    tint = CaribbeanGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.xs))

        Text(
            text = "${product.price} ${product.currency}",
            style = MaterialTheme.typography.titleSmall,
            color = CaribbeanGreen
        )

        Text(
            text = product.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            textAlign = TextAlign.Center
        )

        Text(
            text = product.subTitle,
            style = MaterialTheme.typography.labelSmall,
            color = DustyGray
        )
    }
}