package app.sahhamarket.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.sahhamarket.domain.model.Product
import app.sahhamarket.resources.R


@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    var quantity by remember { mutableStateOf(0) }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(8.dp)
            .width(130.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp).background(Color.White)
        ) {
            Box(Modifier.align(Alignment.End)) {
                Image(
                    painter = painterResource(R.drawable.ic_wholesale),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                // Discount badge
                if (true) {
                    Text(
                        text = "-20%",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(Color.Red, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 1.dp)
                    )
                }
            }
            if (quantity > 0) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(horizontal = 8.dp,),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            quantity--
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Text("-", fontSize = 18.sp, color = Color(0xFF00BFA6))
                    }

                    Text(
                        text = "$quantity",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 4.dp),
                        color = Color(0xFF00796B)
                    )

                    IconButton(
                        onClick = {
                            quantity++
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Text("+", fontSize = 18.sp, color = Color(0xFF00BFA6))
                    }
                }
            }else {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.End)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { quantity++ },
                        modifier = Modifier.size(24.dp), // Smaller tap target
                        content = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color(0xFF00BFA6),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    )
                }
            }
            Text(
                text = product.title,
                fontSize = 14.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF00796B),
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                text = product.subTitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
                modifier = Modifier.align(Alignment.Start)
            )

//            Text(
//                text = product.price,
//                fontSize = 10.sp,
//                color = Color.Gray,
//                textAlign = TextAlign.Left,
//                modifier = Modifier.align(Alignment.Start)
//            )
        }
    }
}
