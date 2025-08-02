package app.sahhamarket.presentation.notification.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.sahhamarket.presentation.notification.components.Notification
import app.sahhamarket.presentation.notification.components.NotificationComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {
    val notifications = remember {
        listOf(
            Notification(
                id = 1,
                icon = Icons.Default.ShoppingCart,
                title = "Black Friday's here to stay.",
                description = "Our offers and cashback will make it feel like Black Friday all year.",
                date = "24. November"
            ),
            Notification(
                id = 2,
                icon = Icons.Default.Home,
                title = "Lorem ipsum text here.",
                description = "This is another lorem ipsum text. It can be very short.",
                date = "24. November"
            ),
            Notification(
                id = 3,
                icon = Icons.Default.Home,
                title = "Lorem ipsum text here.",
                description = "This is another lorem ipsum text. It can be very short. Or it can be very very long, 3 lines for example.",
                date = "24. November"
            ),
            Notification(
                id = 4,
                icon = Icons.Default.ShoppingCart,
                title = "Lorem ipsum here.",
                description = "It can be just a simple line of text.",
                date = "21. November"
            ),
            Notification(
                id = 5,
                icon = Icons.Default.ShoppingCart,
                title = "Black Friday's here to stay.",
                description = "Our offers and cashback will make it feel like Black Friday all year.",
                date = "05. November"
            ),
            Notification(
                id = 6,
                icon = Icons.Default.ShoppingCart,
                title = "Lorem ipsum text here.",
                description = "Lorem ipsum text goes here. It can be very long, very short or a medium text. Depends on the notification type.",
                date = "05. November"
            ),
            Notification(
                id = 7,
                icon = Icons.Default.ShoppingCart,
                title = "Lorem ipsum text goes here",
                description = "This is another lorem ipsum text. It can be very short.",
                date = "05. November"
            ),
            Notification(
                id = 8,
                icon = Icons.Default.Home,
                title = "Black Friday's here to stay.",
                description = "Our offers and cashback will make it feel like Black Friday all year.",
                date = "29. October"
            ),
            Notification(
                id = 9,
                icon = Icons.Default.ShoppingCart,
                title = "Lorem ipsum here.",
                description = "It can be just a simple line of text.",
                date = "29. October"
            )
        )
    }

    val groupedNotifications = notifications.groupBy { it.date }

    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            groupedNotifications.forEach { (date, notificationList) ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = date,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(notificationList) { notification ->
                    NotificationComponent(
                        icon = notification.icon,
                        title = notification.title,
                        description = notification.description,
                        onMoreOptionsClick = {
                            println("More options clicked for notification: ${notification.title}")
                        }
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        onClick = {
                            println("See more clicked")
                        }
                    ) {
                        Text(
                            text = "See more",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationComponentPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NotificationComponent(
                icon = Icons.Default.ShoppingCart,
                title = "Black Friday's here to stay.",
                description = "Our offers and cashback will make it feel like Black Friday all year.",
                onMoreOptionsClick = { }
            )

            NotificationComponent(
                icon = Icons.Default.Home,
                title = "Lorem ipsum text here.",
                description = "This is another lorem ipsum text. It can be very short.",
                onMoreOptionsClick = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    MaterialTheme {
        NotificationScreen()
    }
}