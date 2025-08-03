package app.sahhamarket.presentation.details.product.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.FunGreen
import app.sahhamarket.compose.theme.JaggedIce
import app.sahhamarket.compose.theme.JungleGreen
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.WildSand
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.ProductDetails
import app.sahhamarket.presentation.details.product.vm.ProductDetailsViewModel
import app.sahhamarket.presentation.home.components.DailyReceiptContent
import app.sahhamarket.presentation.home.components.HeaderSectionContent
import app.sahhamarket.presentation.product.components.ProductContent
import app.sahhamarket.resources.R
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    productId: Long,
) {

    LaunchedEffect(Unit) {
        viewModel.processAction(ProductDetailsViewModel.UiAction.InitData(productId))
    }

    val state by viewModel.stateProduct.collectAsStateWithLifecycle()

    ProductDetailContent(
        state = state,
        onAddProductToCart = {
            viewModel.processAction(
                ProductDetailsViewModel.UiAction.AddProduct(it)
            )
        },
        onAddQuantity = { viewModel.processAction(ProductDetailsViewModel.UiAction.AddProduct(it)) },
        onLessQuantity = {
            viewModel.processAction(
                ProductDetailsViewModel.UiAction.LessQuantityProduct(
                    it
                )
            )
        },
    )
}

@Composable
fun ProductDetailContent(
    state: ProductDetailsViewModel.ProductUiState,
    onAddProductToCart: (Product) -> Unit,
    onAddQuantity: (Product) -> Unit,
    onLessQuantity: (Product) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        when (state) {
            is ProductDetailsViewModel.ProductUiState.Loading -> {}
            is ProductDetailsViewModel.ProductUiState.Error -> {}
            is ProductDetailsViewModel.ProductUiState.Success -> {
                ProductDetailsContent(
                    productDetails = state.productDetails,
                    onAddProductToCart = { onAddProductToCart(state.productDetails.product) },
                    onAddQuantity = { onAddQuantity(state.productDetails.product) },
                    onLessQuantity = { onLessQuantity(state.productDetails.product) }
                )
            }
        }
    }
}

@Composable
private fun ProductDetailsContent(
    productDetails: ProductDetails,
    onAddProductToCart: () -> Unit,
    onAddQuantity: () -> Unit,
    onLessQuantity: () -> Unit,
) {
    productDetails.apply {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(horizontal = MaterialTheme.spacing.m)
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.imageUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.ms)
                        .fillMaxWidth()
                        .background(WildSand, RoundedCornerShape(MaterialTheme.spacing.ms))
                        .height(302.dp),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.ms)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = product.title,
                            style = MaterialTheme.typography.titleLarge,
                        )

                        Text(
                            text = stringResource(R.string.txt_price).format(
                                product.price,
                                product.currency
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = DustyGray,
                            modifier = Modifier.padding(top = MaterialTheme.spacing.xxxs)
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.bookmark),
                        contentDescription = stringResource(id = R.string.txt_bookmark),
                        modifier = Modifier
                            .size(MaterialTheme.spacing.m)
                            .clickable { }
                    )
                }
            }

            item {
                Row(
                    horizontalArrangement = if (product.quantity > 0) Arrangement.SpaceBetween else Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.ms),
                ) {
                    AnimatedVisibility(visible = product.quantity > 0) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(color = JaggedIce, shape = MaterialTheme.shapes.small)
                                .padding(all = MaterialTheme.spacing.xs)
                        ) {
                            IconButton(
                                onClick = { onLessQuantity() },
                                modifier = Modifier
                                    .background(White, CircleShape)
                                    .size(MaterialTheme.spacing.l),
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_less),
                                    contentDescription = stringResource(id = R.string.txt_decrease_quantity),
                                    tint = FountainBlue,
                                    modifier = Modifier.padding(all = MaterialTheme.spacing.xs)
                                )
                            }
                            Text(
                                text = product.quantity.toString(),
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.s),
                                style = MaterialTheme.typography.headlineSmall,
                                color = FountainBlue
                            )
                            IconButton(
                                onClick = { onAddQuantity() },
                                modifier = Modifier
                                    .background(White, CircleShape)
                                    .size(MaterialTheme.spacing.l),
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                                    contentDescription = stringResource(id = R.string.txt_increase_quantity),
                                    tint = FountainBlue,
                                    modifier = Modifier.padding(all = MaterialTheme.spacing.xs)
                                )
                            }
                        }
                    }

                    Button(
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = JungleGreen,
                            contentColor = FunGreen
                        ),
                        modifier = Modifier.height(IntrinsicSize.Max),
                        onClick = { onAddProductToCart() }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.m),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.txt_btn_add_to_cart),
                                color = White,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = stringResource(R.string.txt_price).format(
                                    product.price * if (product.quantity == 0) 1 else product.quantity,
                                    product.currency
                                ),
                                color = White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            item {
                var isExpanded by remember { mutableStateOf(false) }
                val rotationAngle by animateFloatAsState(
                    targetValue = if (isExpanded) 90F else 0F,
                    label = ""
                )

                HorizontalDivider(modifier = Modifier.padding(top = MaterialTheme.spacing.ms))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isExpanded = !isExpanded
                        }
                        .padding(vertical = MaterialTheme.spacing.ms)
                ) {
                    Text(
                        text = stringResource(R.string.txt_product_description),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Black,
                        modifier = Modifier.weight(1F)
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
                        contentDescription = null,
                        modifier = Modifier
                            .size(MaterialTheme.spacing.s)
                            .graphicsLayer { rotationZ = rotationAngle },
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(bottom = MaterialTheme.spacing.ms))

                if (isExpanded) {
                    Text(
                        text = LoremIpsum(50).values.joinToString(" "),
                        style = MaterialTheme.typography.labelMedium,
                        color = Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                HeaderSectionContent(
                    title = stringResource(R.string.txt_header_similar_products),
                    modifier = Modifier
                        .background(color = White)
                        .padding(vertical = MaterialTheme.spacing.s)
                )

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(similarProducts) { product ->
                        ProductContent(
                            product = product,
                            modifier = Modifier,
                            onAddToCart = onAddProductToCart,
                            onAddQuantity = onAddProductToCart,
                            onLessQuantity = onLessQuantity
                        )
                    }
                }
            }

            item {
                HeaderSectionContent(
                    title = stringResource(R.string.txt_header_related_recipes),
                    modifier = Modifier
                        .background(color = White)
                        .padding(vertical = MaterialTheme.spacing.s)
                )

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(productDetails.recipes) { receipt ->
                        DailyReceiptContent(
                            receipt = receipt,
                            modifier = Modifier.clickable {}
                        )
                    }
                }
            }

            item {
                HeaderSectionContent(
                    title = stringResource(R.string.txt_header_previous_order),
                    modifier = Modifier
                        .background(color = White)
                        .padding(vertical = MaterialTheme.spacing.s)
                )
            }
        }
    }
}