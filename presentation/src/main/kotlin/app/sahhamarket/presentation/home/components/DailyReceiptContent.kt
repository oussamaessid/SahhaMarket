package app.sahhamarket.presentation.home.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Alabaster
import app.sahhamarket.compose.theme.Alto
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.BrightSun
import app.sahhamarket.compose.theme.CatskillWhite
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.OxfordBlue
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Receipt
import app.sahhamarket.resources.R
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun DailyReceiptContent(
    receipt: Receipt,
    onAddToCart: () -> Unit,
    onAddQuantity: () -> Unit,
    onLessQuantity: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var quantity by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .width(197.dp)
            .height(IntrinsicSize.Max)
            .background(color = Alabaster, shape = MaterialTheme.shapes.small)
            .border(
                width = MaterialTheme.spacing.unit,
                color = CatskillWhite,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5 / 3F)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(receipt.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .clip(shape = MaterialTheme.shapes.small),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(MaterialTheme.spacing.s)
                    .size(MaterialTheme.spacing.m)
                    .background(
                        color = White,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_favorite),
                    contentDescription = "Add to favorites",
                    tint = Color.Gray,
                )
            }
        }

        Text(
            text = receipt.title,
            style = MaterialTheme.typography.bodyLarge,
            color = OxfordBlue,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(end = MaterialTheme.spacing.xs)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.xs,
                    horizontal = MaterialTheme.spacing.ms)
        ) {
            Text(
                text = context.resources.getQuantityString(
                    R.plurals.txt_ingredients_count,
                    receipt.ingredients.size, receipt.ingredients.size
                ),
                style = MaterialTheme.typography.labelMedium,
                color = OxfordBlue,
                modifier = Modifier.padding(end = MaterialTheme.spacing.xs)
            )

            Box(
                modifier = Modifier
                    .background(color = Black, shape = CircleShape)
                    .size(MaterialTheme.spacing.xxxs)
            )

            Text(
                text = receipt.time,
                style = MaterialTheme.typography.labelMedium,
                color = OxfordBlue,
                modifier = Modifier.padding(start = MaterialTheme.spacing.xs)
            )
        }

        Text(
            text = stringResource(R.string.txt_price).format(receipt.price, receipt.currency),
            style = MaterialTheme.typography.bodyLarge,
            color = FountainBlue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.xs,
                    horizontal = MaterialTheme.spacing.ms)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.xs,
                    horizontal = MaterialTheme.spacing.ms
                )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_star),
                contentDescription = null,
                tint = BrightSun,
                modifier = Modifier.size(MaterialTheme.spacing.s)
            )
            Text(
                text = "4.3 (128)",
                style = MaterialTheme.typography.bodySmall,
                color = Black60,
                modifier = Modifier.padding(start = MaterialTheme.spacing.xxxs)
            )
        }
        HorizontalDivider(
            color = Alto,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xs, bottom = MaterialTheme.spacing.xs)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(MaterialTheme.spacing.xl)
        ) {
            this@Column.AnimatedVisibility(visible = quantity < 1) {
                TextButton(
                    onClick = {
                        quantity = 1
                        onAddToCart()
                    },
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
                            modifier = Modifier.size(MaterialTheme.spacing.xxm)
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

            this@Column.AnimatedVisibility(visible = quantity > 0) {
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
                        onClick = {
                            if (quantity > 0) {
                                quantity -= 1
                                onLessQuantity()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_less),
                            contentDescription = null,
                            tint = FountainBlue,
                            modifier = Modifier.padding(MaterialTheme.spacing.xxs)
                        )
                    }

                    Text(
                        text = "$quantity",
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
                        onClick = {
                            quantity += 1
                            onAddQuantity()
                        }
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