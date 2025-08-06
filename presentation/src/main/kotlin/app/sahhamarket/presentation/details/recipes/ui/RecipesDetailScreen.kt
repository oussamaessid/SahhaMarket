package app.sahhamarket.presentation.details.recipes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.darkBluishGray
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.DailyReceipt
import app.sahhamarket.presentation.details.recipes.components.DescriptionComponent
import app.sahhamarket.presentation.details.recipes.components.IngredientsComponent
import app.sahhamarket.presentation.details.recipes.components.OtherRecipesComponent
import app.sahhamarket.presentation.details.recipes.components.TutorialComponent
import app.sahhamarket.resources.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dailyReceipts = listOf(
        DailyReceipt(
            id = 1,
            name = "Heirloom Tomato Salad with Garlic and Rosemary",
            description = "A fresh and vibrant salad featuring heirloom tomatoes, aromatic garlic, and fragrant rosemary. This dish combines the sweetness of ripe tomatoes with the earthy flavors of herbs, creating a perfect balance of taste and nutrition.",
            ingredients = "Heirloom Tomatoes,Fresh Garlic,Rosemary,Olive Oil,Sea Salt,Black Pepper",
            cookingTime = "15 min",
            price = "6.3 SAR",
            imageRes = R.drawable.spaghetti_pesto,
            isFavorite = true,
            ingredientImages = mapOf(
                "Heirloom Tomatoes" to R.drawable.pomodorini,
                "Fresh Garlic" to R.drawable.pomodorini,
                "Rosemary" to R.drawable.pomodorini,
                "Olive Oil" to R.drawable.pomodorini,
                "Sea Salt" to R.drawable.pomodorini,
                "Black Pepper" to R.drawable.pomodorini
            )
        ),
        DailyReceipt(
            id = 2,
            name = "Watermelon and Tomato Feta Salad",
            description = "Refreshing summer salad with watermelon and feta",
            ingredients = "Watermelon,Tomatoes,Feta Cheese,Mint,Olive Oil",
            cookingTime = "15 min",
            price = "6.3 SAR",
            imageRes = R.drawable.chicken_tikka,
            isFavorite = false,
            ingredientImages = mapOf(
                "Watermelon" to R.drawable.pomodorini,
                "Tomatoes" to R.drawable.pomodorini,
                "Feta Cheese" to R.drawable.pomodorini,
                "Mint" to R.drawable.pomodorini,
                "Olive Oil" to R.drawable.pomodorini
            )
        ),
        DailyReceipt(
            id = 3,
            name = "Tomato Basil Squash",
            description = "Hearty squash dish with tomatoes and basil",
            ingredients = "Squash,Tomatoes,Fresh Basil,Garlic,Parmesan",
            cookingTime = "30 min",
            price = "7.2 SAR",
            imageRes = R.drawable.pomodorini,
            isFavorite = true,
            ingredientImages = mapOf(
                "Squash" to R.drawable.pomodorini,
                "Tomatoes" to R.drawable.pomodorini,
                "Fresh Basil" to R.drawable.pomodorini,
                "Garlic" to R.drawable.pomodorini,
                "Parmesan" to R.drawable.pomodorini
            )
        )
    )

    val receipt = dailyReceipts.first()

    RecipesDetailContent(
        receipt = receipt,
        otherRecipes = dailyReceipts.filter { it.id != receipt.id },
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesDetailContent(
    receipt: DailyReceipt,
    otherRecipes: List<DailyReceipt>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isFavorite by remember { mutableStateOf(receipt.isFavorite) }
    var expandedSection by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(MaterialTheme.spacing.s)
        ) {
            Box {
                Image(
                    painter = painterResource(id = receipt.imageRes),
                    contentDescription = receipt.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.s))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.s,
                        vertical = MaterialTheme.spacing.xs
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = receipt.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.size(40.dp)
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isFavorite) R.drawable.bookmark else R.drawable.unbookmarked
                        ),
                        contentDescription = "Bookmark",
                        modifier = Modifier.size(MaterialTheme.spacing.s)
                    )
                }
            }

            Text(
                text = "by Tomas Schuster",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(top = MaterialTheme.spacing.xxs)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.m))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expandedSection =
                            if (expandedSection == "ingredients") null else "ingredients"
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.bodyLarge,
                    color = darkBluishGray
                )
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "View ingredients",
                    tint = darkBluishGray
                )
            }
            if (expandedSection == "ingredients") {
                IngredientsComponent(
                    ingredients = receipt.ingredients.split(","),
                    ingredientImages = receipt.ingredientImages
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.s))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expandedSection =
                            if (expandedSection == "description") null else "description"
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dish description",
                    style = MaterialTheme.typography.bodyLarge,
                    color = darkBluishGray
                )
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "View description",
                    tint = darkBluishGray
                )
            }
            if (expandedSection == "description") {
                DescriptionComponent(
                    description = receipt.description,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.xs)
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.s))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expandedSection = if (expandedSection == "tutorial") null else "tutorial"
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cooking tutorial",
                    style = MaterialTheme.typography.bodyLarge,
                    color = darkBluishGray
                )
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "View tutorial",
                    tint = darkBluishGray
                )
            }
            if (expandedSection == "tutorial") {
                TutorialComponent(
                    steps = listOf(
                        "Cut the onion, red peppers and bacon into small pieces.",
                        "Heat some olive oil in a pan and fry the onion, red peppers and bacon.",
                        "Add oregano, garlic, tomatoes and water and cook for 20 minutes.",
                        "Cook the pasta in a big pot of boiling water."
                    )
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.l))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Other recipes",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "by Tomas Schuster",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Text(
                    text = "See all",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF4CAF50),
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.s))

            OtherRecipesComponent(recipes = otherRecipes)

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}