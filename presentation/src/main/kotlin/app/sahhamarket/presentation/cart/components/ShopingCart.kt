package app.sahhamarket.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.sahhamarket.domain.model.CartResponnse

@Composable
fun ShopingCard(cart: CartResponnse?) {
    Column {
        if ((cart?.total ?: 0) < 100) {
            Row(
                modifier = Modifier
                    .background(Color(0xFFFFF1F0))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "You haven’t reached the minimum order value of 100SAR to get free delivery",
                    color = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            cart?.items?.forEach {
                ProductCartItem(it)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
