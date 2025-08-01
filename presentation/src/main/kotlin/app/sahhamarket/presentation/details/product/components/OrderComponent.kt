package app.sahhamarket.presentation.details.product.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Alto
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.CaribbeanGreen
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.WildSand
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.PreviousOrder
import app.sahhamarket.resources.R

@Composable
fun PreviousOrderSection(
    order: PreviousOrder?,
    onOrderAgainClick: (PreviousOrder) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (order == null) return

    val spacing = MaterialTheme.spacing

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.txt_title_previous_order),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = spacing.s)
        )

        PreviousOrderCard(
            order = order,
            onOrderAgainClick = { onOrderAgainClick(order) }
        )
    }
}

@Composable
fun PreviousOrderCard(
    order: PreviousOrder,
    onOrderAgainClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = MaterialTheme.spacing

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(spacing.ms),
        colors = CardDefaults.cardColors(containerColor = WildSand)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .padding(spacing.s)
                    .weight(1f)
            ) {
                Text(
                    text = order.status,
                    style = MaterialTheme.typography.bodySmall,
                    color = CaribbeanGreen
                )
                Text(
                    text = order.deliveryDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Black,
                    modifier = Modifier.padding(top = spacing.xxxs)
                )

                Spacer(modifier = Modifier.height(spacing.ms))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        order.items.take(3).forEachIndexed { index, product ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(White, CircleShape)
                                    .padding(spacing.xxxs)
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = product.imageUrl.toIntOrNull() ?: R.drawable.pomodorini
                                    ),
                                    contentDescription = stringResource(
                                        id = R.string.txt_order_description,
                                        index + 1
                                    ),
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            if (index < 2) Spacer(modifier = Modifier.width(spacing.xs))
                        }

                        if (order.moreItemsCount > 0) {
                            Spacer(modifier = Modifier.width(spacing.xs))
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Alto, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(
                                        id = R.string.txt_more_items,
                                        order.moreItemsCount
                                    ),
                                    style = MaterialTheme.typography.labelSmall,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(spacing.ms))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.txt_order_id, order.orderId),
                            style = MaterialTheme.typography.bodySmall,
                            color = DustyGray
                        )

                        Text(
                            text = stringResource(id = R.string.txt_final_total, order.finalTotal),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(top = spacing.xxxs)
                        )
                    }

                    Button(
                        onClick = onOrderAgainClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CaribbeanGreen
                        ),
                        shape = RoundedCornerShape(spacing.m),
                        contentPadding = PaddingValues(
                            horizontal = spacing.xs,
                            vertical = spacing.xxxs
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_btn_order_again),
                            color = White,
                            style = MaterialTheme.typography.labelMedium,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .background(Red)
                    .fillMaxHeight()
                    .width(spacing.xl),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Order\nAgain\n&\nGet\nFlat\n10%\nOFF",
                    style = MaterialTheme.typography.bodyMedium,
                    color = White,
                    textAlign = TextAlign.Center,
                    lineHeight = MaterialTheme.typography.bodyMedium.fontSize * 1.2
                )
            }
        }
    }
}