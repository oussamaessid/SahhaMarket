package app.sahhamarket.presentation.details.product.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import app.sahhamarket.compose.theme.*
import app.sahhamarket.domain.model.DailyReceipt
import app.sahhamarket.resources.R
import androidx.compose.ui.unit.dp

@Composable
fun RelatedRecipesSection(
    recipes: List<DailyReceipt>,
    onSeeAllClick: () -> Unit,
    onRecipeClick: (DailyReceipt) -> Unit,
    onFavoriteClick: (DailyReceipt, Boolean) -> Unit,
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
                text = stringResource(id = R.string.txt_header_related_recipes),
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
            items(recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = { onRecipeClick(recipe) },
                    onFavoriteClick = { isFavorite -> onFavoriteClick(recipe, isFavorite) }
                )
            }
        }
    }
}

@Composable
fun RecipeCard(
    recipe: DailyReceipt,
    onClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = MaterialTheme.spacing
    var isFavorite by remember { mutableStateOf(recipe.isFavorite) }

    Card(
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(spacing.ms),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box {
            Column(modifier = Modifier.padding(spacing.ms)) {
                Image(
                    painter = painterResource(id = recipe.imageRes),
                    contentDescription = recipe.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(spacing.xs)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(spacing.xs))

                Text(
                    text = recipe.price,
                    style = MaterialTheme.typography.titleMedium,
                    color = Black
                )

                Text(
                    text = stringResource(id = R.string.txt_cooking_time, recipe.cookingTime),
                    style = MaterialTheme.typography.labelSmall,
                    color = DustyGray
                )

                Spacer(modifier = Modifier.height(spacing.xxxs))

                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    onFavoriteClick(isFavorite)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(spacing.xs)
                    .size(32.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = if (isFavorite) R.drawable.bookmark else R.drawable.unbookmarked
                    ),
                    contentDescription = stringResource(id = R.string.txt_btn_add_favorites),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}