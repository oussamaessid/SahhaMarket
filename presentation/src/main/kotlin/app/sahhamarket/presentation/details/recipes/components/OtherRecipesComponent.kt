package app.sahhamarket.presentation.details.recipes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.DailyReceipt
import app.sahhamarket.resources.R

@Composable
fun OtherRecipesComponent(
    recipes: List<DailyReceipt>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ms),
        modifier = modifier
    ) {
        items(recipes) { recipe ->
            OtherRecipeCard(recipe = recipe)
        }
    }
}

@Composable
private fun OtherRecipeCard(recipe: DailyReceipt) {
    var isFavorite by remember { mutableStateOf(recipe.isFavorite) }

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        shape = RoundedCornerShape(MaterialTheme.spacing.ms),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box {
            Column(modifier = Modifier.padding(MaterialTheme.spacing.ms)) {
                Image(
                    painter = painterResource(id = recipe.imageRes),
                    contentDescription = recipe.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(MaterialTheme.spacing.xs)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))

                Text(
                    text = recipe.price,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Black
                )

                Text(
                    text = "Cooking time: ${recipe.cookingTime}",
                    style = MaterialTheme.typography.labelLarge,
                    color = Gray
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xxs))

                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(MaterialTheme.spacing.xs)
                    .size(MaterialTheme.spacing.l)
            ) {
                Image(
                    painter = painterResource(id = if (isFavorite) R.drawable.bookmark else R.drawable.unbookmarked),
                    contentDescription = "Bookmark",
                    modifier = Modifier.size(MaterialTheme.spacing.s)
                )
            }
        }
    }
}