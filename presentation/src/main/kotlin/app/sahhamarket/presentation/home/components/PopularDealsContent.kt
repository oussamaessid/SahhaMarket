package app.sahhamarket.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.components.drawDiagonalLabel
import app.sahhamarket.compose.theme.BaliHai
import app.sahhamarket.compose.theme.CatskillWhite
import app.sahhamarket.compose.theme.CoralRed
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.FunGreen
import app.sahhamarket.compose.theme.JaggedIce
import app.sahhamarket.compose.theme.Nugget
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.resources.R
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun PopularDealsContent(
    product: Product,
    onAddProductToCart: () -> Unit,
    onAddQuantity: () -> Unit,
    onLessQuantity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .width(170.dp)
            .height(intrinsicSize = IntrinsicSize.Max)
            .background(color = White, shape = MaterialTheme.shapes.small)
            .border(
                width = MaterialTheme.spacing.unit,
                color = CatskillWhite,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
                .background(
                    color = White,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .then(
                        Modifier.drawDiagonalLabel(
                            text = stringResource(R.string.txt_promo).format(product.promo?.value),
                            color = CoralRed
                        )
                    ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }

        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineMedium,
            color = BaliHai,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.xs,
                    horizontal = MaterialTheme.spacing.ms,
                )
        )

        Text(
            text = stringResource(R.string.txt_price).format(product.price, product.currency),
            style = MaterialTheme.typography.bodyLarge,
            color = Nugget,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.ms)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.xxxs,
                    horizontal = MaterialTheme.spacing.ms
                )
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_star),
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.txt_rating).format(
                    product.rating.value,
                    product.rating.number
                ),
                style = MaterialTheme.typography.labelMedium,
                color = BaliHai,
                modifier = Modifier.padding(end = MaterialTheme.spacing.xxxs)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding( MaterialTheme.spacing.ms )
                .height(MaterialTheme.spacing.xl)
                .align(Alignment.CenterHorizontally)
        ) {
            AnimatedVisibility(visible = product.quantity < 1) {
                Button(
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = JaggedIce,
                        contentColor = FunGreen
                    ),
                    onClick = { onAddProductToCart() }
                ) {
                    Text(
                        text = stringResource(R.string.txt_btn_add_to_cart),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

            AnimatedVisibility(visible = product.quantity > 0) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    IconButton(
                        modifier = Modifier
                            .background(color = FountainBlue, shape = MaterialTheme.shapes.small)
                            .size(MaterialTheme.spacing.l),
                        onClick = { onLessQuantity() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_less),
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.padding(MaterialTheme.spacing.xxs)
                        )
                    }

                    Text(
                        text = "${product.quantity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = FountainBlue,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(1F)
                            .padding(horizontal = MaterialTheme.spacing.xs)
                    )

                    IconButton(
                        modifier = Modifier
                            .background(color = FountainBlue, shape = MaterialTheme.shapes.small)
                            .size(MaterialTheme.spacing.l),
                        onClick = { onAddQuantity() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.padding(MaterialTheme.spacing.xxs)
                        )
                    }
                }
            }
        }
    }
}