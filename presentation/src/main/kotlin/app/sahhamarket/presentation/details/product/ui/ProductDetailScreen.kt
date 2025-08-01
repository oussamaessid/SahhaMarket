package app.sahhamarket.presentation.details.product.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Alto
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.CaribbeanGreen
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.JaggedIce
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.WildSand
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.DailyReceipt
import app.sahhamarket.domain.model.PreviousOrder
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.Promo
import app.sahhamarket.domain.model.Rating
import app.sahhamarket.domain.model.Status
import app.sahhamarket.presentation.details.product.components.PreviousOrderSection
import app.sahhamarket.presentation.details.product.components.RelatedRecipesSection
import app.sahhamarket.presentation.details.product.components.SimilarProductsSection
import app.sahhamarket.resources.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String?,
    onBackClick: () -> Unit = {},
    onAddToCart: (Product, Int) -> Unit = { _, _ -> },
    onSimilarProductClick: (Product) -> Unit = {},
    onRecipeClick: (DailyReceipt) -> Unit = {},
    onOrderAgain: (PreviousOrder) -> Unit = {},
) {
    val product = when (productId) {
        "pomodorini" -> Product(
            id = 1L,
            imageUrl = "pomodorini.jpg",
            title = "Cherrytomaten 250g (Marokko)",
            subTitle = "Vegetables",
            price = 3.45f,
            currency = "SAR",
            promo = null,
            rating = Rating(value = 4.5f, number = 100),
            quantity = 1,
            status = Status.AVAILABLE
        )
        "yellowCherry" -> Product(
            id = 2L,
            imageUrl = "yellow_cherry.jpg",
            title = "Yellow Cherry Tomatoes 250g",
            subTitle = "Vegetables",
            price = 1.60f,
            currency = "SAR",
            promo = null,
            rating = Rating(value = 4.0f, number = 80),
            quantity = 1,
            status = Status.AVAILABLE
        )
        "calypsoTom" -> Product(
            id = 3L,
            imageUrl = "calypso_tom.jpg",
            title = "Calypso Tomatoes 200g (Egypt)",
            subTitle = "Vegetables",
            price = 1.80f,
            currency = "SAR",
            promo = null,
            rating = Rating(value = 4.2f, number = 90),
            quantity = 1,
            status = Status.AVAILABLE
        )
        else -> Product(
            id = 0L,
            imageUrl = "pomodorini.jpg",
            title = "Cherrytomaten 250g (Marokko)",
            subTitle = "Vegetables",
            price = 3.45f,
            currency = "SAR",
            promo = null,
            rating = Rating(value = 4.5f, number = 100),
            quantity = 1,
            status = Status.AVAILABLE
        )
    }

    val similarProducts = listOf(
        Product(
            id = 1L,
            imageUrl = "yellow_cherry.jpg",
            title = "Yellow Cherry Tomatoes 250g",
            subTitle = "Vegetables",
            price = 1.60f,
            currency = "SAR",
            promo = null,
            rating = Rating(value = 4.0f, number = 80),
            quantity = 1,
            status = Status.AVAILABLE
        ),
        Product(
            id = 2L,
            imageUrl = "pomodorini.jpg",
            title = "Cherrytomaten 250g (Marokko)",
            subTitle = "Vegetables",
            price = 1.20f,
            currency = "SAR",
            promo = null,
            rating = Rating(value = 4.5f, number = 100),
            quantity = 1,
            status = Status.AVAILABLE
        ),
        Product(
            id = 3L,
            imageUrl = "calypso_tom.jpg",
            title = "Calypso Tomatoes 200g (Egypt)",
            subTitle = "Vegetables",
            price = 1.80f,
            currency = "SAR",
            promo = null,
            rating = Rating(value = 4.2f, number = 90),
            quantity = 1,
            status = Status.AVAILABLE
        )
    )

    val relatedRecipes = listOf(
        DailyReceipt(
            id = 1,
            name = "Watermelon and Tomato Feta Salad",
            description = "A refreshing salad with watermelon and tomatoes",
            ingredients = "Watermelon, Tomatoes, Feta Cheese",
            cookingTime = "15min",
            price = "6.3 SAR",
            imageRes = R.drawable.chicken_tikka,
            isFavorite = false,
            ingredientImages = mapOf("Tomatoes" to R.drawable.pomodorini)
        ),
        DailyReceipt(
            id = 2,
            name = "Heirloom Tomato Salad with Rosemary",
            description = "A flavorful salad with heirloom tomatoes",
            ingredients = "Heirloom Tomatoes, Rosemary, Olive Oil",
            cookingTime = "30min",
            price = "6.3 SAR",
            imageRes = R.drawable.spaghetti_pesto,
            isFavorite = false,
            ingredientImages = mapOf("Tomatoes" to R.drawable.pomodorini)
        ),
        DailyReceipt(
            id = 3,
            name = "Tomato Squid",
            description = "A seafood dish with tomatoes",
            ingredients = "Squid, Tomatoes, Garlic",
            cookingTime = "25min",
            price = "6.3 SAR",
            imageRes = R.drawable.chicken_tikka,
            isFavorite = false,
            ingredientImages = mapOf("Tomatoes" to R.drawable.pomodorini)
        )
    )

    val previousOrder = PreviousOrder(
        orderId = "#28292999",
        deliveryDate = "On Wed, 27 Jul 2022",
        status = "Delivered",
        finalTotal = "SAR 123.9",
        items = listOf(
            Product(
                id = 4L,
                imageUrl = "retail.jpg",
                title = "Retail Item",
                subTitle = "Misc",
                price = 10.0f,
                currency = "SAR",
                promo = null,
                rating = Rating(value = 4.0f, number = 50),
                quantity = 1,
                status = Status.AVAILABLE
            ),
            Product(
                id = 2L,
                imageUrl = "pomodorini.jpg",
                title = "Cherrytomaten 250g (Marokko)",
                subTitle = "Vegetables",
                price = 3.45f,
                currency = "SAR",
                promo = null,
                rating = Rating(value = 4.5f, number = 100),
                quantity = 1,
                status = Status.AVAILABLE
            ),
            Product(
                id = 5L,
                imageUrl = "retail.jpg",
                title = "Another Retail Item",
                subTitle = "Misc",
                price = 15.0f,
                currency = "SAR",
                promo = null,
                rating = Rating(value = 4.0f, number = 50),
                quantity = 1,
                status = Status.AVAILABLE
            )
        ),
        moreItemsCount = 5
    )

    ProductDetailContent(
        product = product,
        similarProducts = similarProducts,
        relatedRecipes = relatedRecipes,
        previousOrder = previousOrder,
        onBackClick = onBackClick,
        onAddToCart = onAddToCart,
        onSimilarProductClick = onSimilarProductClick,
        onRecipeClick = onRecipeClick,
        onOrderAgain = onOrderAgain
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailContent(
    product: Product,
    similarProducts: List<Product>,
    relatedRecipes: List<DailyReceipt>,
    previousOrder: PreviousOrder?,
    onBackClick: () -> Unit,
    onAddToCart: (Product, Int) -> Unit,
    onSimilarProductClick: (Product) -> Unit,
    onRecipeClick: (DailyReceipt) -> Unit,
    onOrderAgain: (PreviousOrder) -> Unit,
) {
    val spacing = MaterialTheme.spacing
    var isBookmarked by remember { mutableStateOf(true) }
    var quantity by remember { mutableIntStateOf(product.quantity) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(vertical = spacing.xs, horizontal = spacing.s)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(WildSand, RoundedCornerShape(spacing.ms)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pomodorini),
                    contentDescription = stringResource(id = R.string.txt_product_image),
                    modifier = Modifier.size(150.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(spacing.s))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { isBookmarked = !isBookmarked },
                    modifier = Modifier.size(MaterialTheme.spacing.m)
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isBookmarked) R.drawable.bookmark else R.drawable.unbookmarked
                        ),
                        contentDescription = stringResource(id = R.string.txt_bookmark),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Text(
                text = "${product.price} ${product.currency}",
                style = MaterialTheme.typography.bodyMedium,
                color = DustyGray,
                modifier = Modifier.padding(top = spacing.xxxs)
            )

            Spacer(modifier = Modifier.height(spacing.m))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.xs),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(JaggedIce, RoundedCornerShape(spacing.xs))
                        .padding(horizontal = spacing.xs, vertical = spacing.xs)
                ) {
                    IconButton(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier
                            .size(MaterialTheme.spacing.l)
                            .background(White, CircleShape)
                            .padding(spacing.xxxs),
                        enabled = quantity > 1
                    ) {
                        Image(
                            painter = painterResource(R.drawable.minus),
                            contentDescription = stringResource(id = R.string.txt_decrease_quantity),
                            colorFilter = ColorFilter.tint(CaribbeanGreen),
                            modifier = Modifier.size(spacing.xs)
                        )
                    }
                    Text(
                        text = quantity.toString(),
                        modifier = Modifier.padding(horizontal = spacing.m),
                        style = MaterialTheme.typography.bodyMedium,
                        color = White
                    )
                    IconButton(
                        onClick = { quantity++ },
                        modifier = Modifier
                            .size(MaterialTheme.spacing.l)
                            .background(White, CircleShape)
                            .padding(spacing.xxxs)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.plus),
                            contentDescription = stringResource(id = R.string.txt_increase_quantity),
                            colorFilter = ColorFilter.tint(CaribbeanGreen),
                            modifier = Modifier.size(spacing.xs)
                        )
                    }
                }

                Button(
                    onClick = { onAddToCart(product, quantity) },
                    modifier = Modifier.height(MaterialTheme.spacing.xl),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CaribbeanGreen
                    ),
                    shape = RoundedCornerShape(spacing.xs)
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_btn_add_to_cart),
                        color = White,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = spacing.s)
                    )
                    Spacer(modifier = Modifier.width(spacing.xs))
                    Text(
                        text = "${product.price * quantity} ${product.currency}",
                        color = White,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.m))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle expand */ }
                    .padding(vertical = spacing.ms),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.txt_product_description),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrowright),
                    contentDescription = stringResource(id = R.string.txt_view_product_description),
                    tint = Black60,
                    modifier = Modifier
                        .size(spacing.m)
                        .clickable { }
                )
            }

            HorizontalDivider(color = Alto)

            Spacer(modifier = Modifier.height(spacing.m))

            SimilarProductsSection(
                products = similarProducts,
                onSeeAllClick = { /* Handle see all similar products */ },
                onProductClick = onSimilarProductClick,
                onAddToCartClick = { product -> /* Handle add similar product to cart */ }
            )

            Spacer(modifier = Modifier.height(spacing.l))

            RelatedRecipesSection(
                recipes = relatedRecipes,
                onSeeAllClick = { /* Handle see all recipes */ },
                onRecipeClick = onRecipeClick,
                onFavoriteClick = { recipe, isFavorite -> /* Handle favorite */ }
            )

            Spacer(modifier = Modifier.height(spacing.l))

            PreviousOrderSection(
                order = previousOrder,
                onOrderAgainClick = onOrderAgain
            )

            Spacer(modifier = Modifier.height(spacing.l))
        }
    }
}