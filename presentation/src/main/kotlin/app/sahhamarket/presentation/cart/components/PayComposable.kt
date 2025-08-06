package app.sahhamarket.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PayComposable() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text("Address", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDF2F7), RoundedCornerShape(14.dp))
                .padding(vertical = 2.dp, horizontal = 5.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text("Oderstrasse 12A, 12030, Berlin", modifier = Modifier.weight(1f))
            TextButton(onClick = { }) {
                Text("Edit" , fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text("Details", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.fillMaxWidth()) {
            Text("Call when you arrive" , modifier = Modifier.weight(1f))
            Checkbox(checked = false, onCheckedChange = {})
        }
        Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.fillMaxWidth()) {
            Text("Leave at the door" , modifier = Modifier.weight(1f))
            Checkbox(checked = true, onCheckedChange = {})
        }
        Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.fillMaxWidth()) {
            Text("Don't ring the bell" , modifier = Modifier.weight(1f))
            Checkbox(checked = false, onCheckedChange = {})
        }

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Here you can add notes for the driver..." , fontSize = 12.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDF2F7), shape = RoundedCornerShape(12.dp))
                .border(0.dp, Color.Transparent),
            maxLines = 2,
            singleLine = false,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFEDF2F7),
                focusedContainerColor = Color(0xFFEDF2F7),
                disabledContainerColor = Color(0xFFEDF2F7),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )


        Text("Max 50 characters", fontSize = 10.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Order schedule", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDF2F7), RoundedCornerShape(2.dp))
                .border(0.dp, Color.Transparent, RoundedCornerShape(2.dp)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDF2F7))


        ) {
            Icon(Icons.Default.Refresh, contentDescription = null , tint = Color.DarkGray)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Add delivery date and time" , color = Color.DarkGray , fontWeight = FontWeight.Normal,fontSize = 14.sp , modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Vouchers
        Text("Vouchers", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDF2F7), RoundedCornerShape(2.dp))
                .border(0.dp, Color.Transparent, RoundedCornerShape(2.dp)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDF2F7))


        ) {
            Icon(Icons.Default.Add, contentDescription = null , tint = Color.DarkGray)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Add voucher code" , color = Color.DarkGray , fontWeight = FontWeight.Normal,fontSize = 14.sp , modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tip Section
        Text("Add a tip", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("1 SAR", "2 SAR", "4 SAR", "6 SAR").forEach {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(it, fontSize = 12.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("Say Thank You with a tip. 100% is for the riders.", fontSize = 10.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Payment method", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDF2F7), RoundedCornerShape(2.dp))
                .border(0.dp, Color.Transparent, RoundedCornerShape(2.dp)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDF2F7))


        ) {
//            Icon(painterResource(id = R.drawable.apple_pay), contentDescription = null , tint = Color.DarkGray , modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Apple Pay" , color = Color.DarkGray , fontWeight = FontWeight.Normal,fontSize = 14.sp , modifier = Modifier.weight(2f))
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null , tint = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Order summary", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            Modifier
                .background(Color(0xFFEDF2F7), RoundedCornerShape(14.dp))
                .padding(12.dp)) {
            Text("Order nr: #582394", color = Color(0xFF22A66B), fontSize = 14.sp , modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Right)
            Spacer(modifier = Modifier.height(10.dp))

            val items = listOf(
                "2 × Broccoli 250g" to "2,80 SAR",
                "1 × Carrots 400g" to "1,60 SAR",
                "2 × Pineapple juice 0.30L" to "5,40 SAR",
                "2 × Turkish Apple" to "2,20 SAR"
            )
            items.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(it.first)
                    Text(it.second)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text("Payment method", fontWeight = FontWeight.SemiBold)
                Text("Apple Pay")
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Delivery")
                Text("1,20 SAR")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Tip")
                Text("2 SAR")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider()
            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total incl. VAT", fontWeight = FontWeight.Bold)
                Text("15,23 SAR", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            buildAnnotatedString {
                append("By placing your order you agree to our ")

                withStyle(style = SpanStyle(color = Color(0xFF2ECC71))) { // Green
                    append("Terms and Conditions")
                }

                append(" and confirm that you have read our rights of ")

                withStyle(style = SpanStyle(color = Color(0xFF2ECC71))) { // Green
                    append("Withdrawal")
                }

                append(" and our ")

                withStyle(style = SpanStyle(color = Color(0xFF2ECC71))) { // Green
                    append("Policy")
                }

                append(".")
            },
            fontSize = 10.sp,
            color = Color.Gray,
            lineHeight = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
//            Icon(painter = painterResource(id = R.drawable.pay), contentDescription = null , modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(24.dp))
            Text("Confirm and Pay 15,23 SAR", color = Color.White, fontSize = 12.sp)
        }
    }


}

