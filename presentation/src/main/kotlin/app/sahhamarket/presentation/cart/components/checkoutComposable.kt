package app.sahhamarket.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.sahhamarket.domain.model.CartResponnse
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.Promo
import app.sahhamarket.domain.model.Rating
import app.sahhamarket.domain.model.Status

@Composable
fun CheckoutComposable(
    cart: CartResponnse?,
    isGoingToPay: Boolean,
    onGoToPay: () -> Unit,
    showSuggestions: Boolean,
    onToggleSuggestions: () -> Unit
) {
Column (
    Modifier.fillMaxSize()
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .weight(1f)
    ) {
        item {
            ShopingCard(cart)
        }
        item {
            Column {
                TextButton(
                    onClick = { onToggleSuggestions() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (showSuggestions ) "Hide suggested products ▲" else "Show suggested products ▼",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
                if(showSuggestions){
                    ProductSection("Drinks" , listOf(
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE),
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE),
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE),
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE)
                    ),showSuggestions)

                    ProductSection("Vegetables" , listOf(
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE),
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE),
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE),
                        Product( id = 1L,
                            imageUrl = "file:///android_asset/ic_pomodorini.png",
                            title = "Pomodorini 250g (Italy)",
                            subTitle = "4.25 SAR / kg",
                            price = 1.54F,
                            currency = "SAR",
                            quantity = 1,
                            promo = Promo(
                                value = 10,
                                price = 1.39F
                            ),
                            rating = Rating(
                                value = 4.7F,
                                number = 123
                            ),
                            status = Status.AVAILABLE)
                    ),showSuggestions)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC8EDD9), shape = RoundedCornerShape(8.dp))
            .clickable { onGoToPay() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Checkout", fontWeight = FontWeight.SemiBold)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = cart?.total.toString() ?: "", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = "plus delivery fee", fontSize = 10.sp)
            }
        }
    }
    Spacer(Modifier.height(10.dp))
}
}

