package app.sahhamarket.presentation.product.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import app.sahhamarket.compose.theme.Alabaster
import app.sahhamarket.compose.theme.Alto
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.BrightSun
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.resources.R
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ProductContent(
    product: Product,
    onAddToCart: () -> Unit,
    onAddQuantity: () -> Unit,
    onLessQuantity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .background(
                color = Alabaster,
                shape = MaterialTheme.shapes.medium
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier
                    .padding(all = MaterialTheme.spacing.ms)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentDescription = null,
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(MaterialTheme.spacing.xs)
                    .size(MaterialTheme.spacing.l)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_favorite),
                    contentDescription = "Add to favorites",
                    tint = Color.Gray,
                )
            }
        }

        Text(
            text = stringResource(R.string.txt_price).format(product.price, product.currency),
            style = MaterialTheme.typography.bodyMedium,
            color = FountainBlue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xs)
        )

        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineSmall,
            color = Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.ms,
                    top = MaterialTheme.spacing.xxxs,
                    end = MaterialTheme.spacing.ms
                )
        )

        Text(
            text = product.subTitle,
            style = MaterialTheme.typography.bodySmall,
            color = Black60,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.ms,
                    top = MaterialTheme.spacing.xxxs,
                    end = MaterialTheme.spacing.ms
                )
        )

        Row(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.xxxs),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_star),
                contentDescription = null,
                tint = BrightSun,
                modifier = Modifier.size(MaterialTheme.spacing.s)
            )
            Text(
                text = stringResource(R.string.txt_rating).format(
                    product.rating.value,
                    product.rating.number
                ),
                style = MaterialTheme.typography.bodySmall,
                color = Black60,
                modifier = Modifier.padding(start = MaterialTheme.spacing.xxxs)
            )
        }

        // Ligne grise pleine largeur
        HorizontalDivider(
            color = Alto,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.s, bottom = MaterialTheme.spacing.xs)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(MaterialTheme.spacing.xl)
        ) {
            this@Column.AnimatedVisibility(visible = product.quantity < 1) {
                TextButton(
                    onClick = { onAddToCart() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_cart),
                            contentDescription = null,
                            tint = FountainBlue,
                            modifier = Modifier.size(MaterialTheme.spacing.m)
                        )
                        Text(
                            text = stringResource(R.string.txt_btn_add_to_cart),
                            style = MaterialTheme.typography.bodySmall,
                            color = Black,
                            modifier = Modifier.padding(start = MaterialTheme.spacing.xs)
                        )
                    }
                }
            }

            this@Column.AnimatedVisibility(visible = product.quantity > 0) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.m)
                ) {
                    IconButton(
                        modifier = Modifier
                            .border(
                                width = MaterialTheme.spacing.unit,
                                color = FountainBlue,
                                shape = CircleShape
                            )
                            .size(MaterialTheme.spacing.l),
                        onClick = { onLessQuantity() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_less),
                            contentDescription = null,
                            tint = FountainBlue,
                            modifier = Modifier.padding(MaterialTheme.spacing.xxs)
                        )
                    }

                    Text(
                        text = "${product.quantity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Black,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(1F)
                            .padding(horizontal = MaterialTheme.spacing.xs)
                    )

                    IconButton(
                        modifier = Modifier
                            .border(
                                width = MaterialTheme.spacing.unit,
                                color = FountainBlue,
                                shape = CircleShape
                            )
                            .size(MaterialTheme.spacing.l),
                        onClick = { onAddQuantity() }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                            contentDescription = null,
                            tint = FountainBlue,
                            modifier = Modifier.padding(MaterialTheme.spacing.xxs)
                        )
                    }
                }
            }
        }
    }
}