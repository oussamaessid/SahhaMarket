package app.sahhamarket.presentation.cart.ui

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.sahhamarket.presentation.cart.components.PayComposable
import app.sahhamarket.presentation.cart.components.CheckoutComposable
import app.sahhamarket.presentation.cart.vm.CartViewModel

@Composable
fun Cart(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isCartExpanded : Boolean,
    onDismiss: () -> Unit

) {
    var isGoingToPay by remember { mutableStateOf(false) }
    var showSuggestions by remember { mutableStateOf(false) }
    val viewModel: CartViewModel = hiltViewModel()
    val cartState by viewModel.stateCart.collectAsState()
    if (isCartExpanded) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .clickable { onDismiss() }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxHeight(0.9f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                    if(isGoingToPay){
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft , contentDescription = null , modifier = Modifier.clickable { isGoingToPay = false })
                    }
                    Text(
                        "Shoping cart",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .background(Color.LightGray, shape = RoundedCornerShape(50.dp))
                            .size(30.dp)
                            .weight(0.1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Black
                        )
                    }

            }
            if (isGoingToPay) {
                PayComposable()
            } else {
                CheckoutComposable(
                    cart = cartState.cart,
                    isGoingToPay = isGoingToPay,
                    onGoToPay = { isGoingToPay = true },
                    showSuggestions = showSuggestions,
                    onToggleSuggestions = {
                        showSuggestions = !showSuggestions
                    }
                )

            }
        }
    }
}