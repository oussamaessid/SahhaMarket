package app.sahhamarket.presentation.details.recipes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R

@Composable
fun IngredientsComponent(
    ingredients: List<String>,
    ingredientImages: Map<String, Int>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ms)
    ) {
        items(ingredients) { ingredient ->
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(MaterialTheme.spacing.ms),
                elevation = CardDefaults.cardElevation(MaterialTheme.spacing.xxs)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.xs),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(
                            id = ingredientImages[ingredient] ?: R.drawable.ic_retail
                        ),
                        contentDescription = "$ingredient Image",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(MaterialTheme.spacing.xs)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.xs))

                    Text(
                        text = ingredient,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}