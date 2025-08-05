package app.sahhamarket.presentation.cart.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.sahhamarket.domain.model.CartProduct
import app.sahhamarket.domain.model.Item
import app.sahhamarket.presentation.cart.vm.AddToCartViewModel
import app.sahhamarket.presentation.cart.vm.UpdateCartItemViewModel
import app.sahhamarket.resources.R



@Composable
fun ProductCartItem(product: Item) {
    var quantity by remember { mutableStateOf(product.quantity.toInt()) }
    val updateCartItemViewModel: UpdateCartItemViewModel = hiltViewModel()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 12.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val yTop = 0f
                val yBottom = size.height - strokeWidth
                drawLine(Color.LightGray, Offset(0f, yTop), Offset(size.width, yTop), strokeWidth)
                drawLine(Color.LightGray, Offset(0f, yBottom), Offset(size.width, yBottom), strokeWidth)
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(R.drawable.pomodorini),
            contentDescription = product.product.name,
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.product.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${product.price} SAR",
                style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF1BABA4))
            )
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF9F9F9))
            ) {
                Button(
                    onClick = {
                        if (quantity > 1) {
                            quantity--
                            updateCartItemViewModel.updateItem(product.copy(quantity = quantity.toLong()))
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(text = "-", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp)
                }

                Text(
                    text = quantity.toString(),
                    modifier = Modifier
                        .background(Color(0xFF1BABA4), RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Button(
                    onClick = {
                        quantity++
                        updateCartItemViewModel.updateItem(product.copy(quantity = quantity.toLong()))
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(text = "+", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp)
                }
            }
        }
    }
}
