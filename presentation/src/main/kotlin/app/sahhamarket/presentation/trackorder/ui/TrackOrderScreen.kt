package app.sahhamarket.presentation.trackorder.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.sahhamarket.compose.theme.ChateauGreen
import app.sahhamarket.resources.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTrackingScreen(
    navController: NavHostController,
) {
    val sheetPeekHeight = 220.dp
    val bottomSheetState = rememberBottomSheetScaffoldState()

    val storeLocation = LatLng(36.8065, 10.1815)
    val houseLocation = LatLng(36.8100, 10.1850)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(storeLocation, 14f)
    }

    val context = LocalContext.current
    var storeIcon by remember { mutableStateOf<BitmapDescriptor?>(null) }
    var driverIcon by remember { mutableStateOf<BitmapDescriptor?>(null) }
    var houseIcon by remember { mutableStateOf<BitmapDescriptor?>(null) }

    // ⛳ MarkerState that updates position
    val driverMarkerState = rememberMarkerState(position = storeLocation)

    // Load bitmap icons
    LaunchedEffect(Unit) {
        try {
            storeIcon = BitmapDescriptorFactory.fromResource(R.drawable.store)
            driverIcon = BitmapDescriptorFactory.fromResource(R.drawable.motorbike)
            houseIcon = BitmapDescriptorFactory.fromResource(R.drawable.house)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Animate driver from store to house
    LaunchedEffect(driverIcon) {
        if (driverIcon != null) {
            val steps = 100
            val delayMillis = 1000L

            for (i in 1..steps) {
                val lat = storeLocation.latitude + (houseLocation.latitude - storeLocation.latitude) * (i / steps.toDouble())
                val lng = storeLocation.longitude + (houseLocation.longitude - storeLocation.longitude) * (i / steps.toDouble())
                driverMarkerState.position = LatLng(lat, lng)
                delay(delayMillis)
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetPeekHeight = sheetPeekHeight,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            SheetContent()
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = rememberMarkerState(position = storeLocation),
                title = "Store",
                snippet = "Insta Grocery Store",
                icon = storeIcon
            )

            Marker(
                state = driverMarkerState,
                title = "Delivery",
                snippet = "Motorcycle",
                icon = driverIcon
            )

            Marker(
                state = rememberMarkerState(position = houseLocation),
                title = "Your Place",
                snippet = "Destination",
                icon = houseIcon
            )

            Polyline(
                points = listOf(storeLocation, houseLocation),
                color = Color.Blue,
                width = 5f
            )
        }
    }
}


@Composable
fun SheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Your order is on the way!", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Driver card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(2.dp, Color.Blue), // Blue border with 2.dp thickness
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White), // White background
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // optional, no shadow
        ) {
            Column(modifier = Modifier.padding(16.dp).background(Color.White)) {
                Text("Motor-cycle Accent - Red", fontWeight = FontWeight.SemiBold)
                Text("11:59 - 10-08", style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_wholesale),
                        contentDescription = "Driver",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Adam Monir", fontWeight = FontWeight.Medium)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                            Text("4.8", style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = { /* Call */ }) {
                        Icon(Icons.Default.Call, contentDescription = "Call")
                    }
                    IconButton(onClick = { /* Message */ }) {
                        Icon(Icons.Default.Email, contentDescription = "Message")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Price & Address
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("200 SAR", fontWeight = FontWeight.Medium, fontSize = 18.sp , color = ChateauGreen)
                Spacer(Modifier.width(10.dp))
                Text("Total price (tax included)", style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Info , contentDescription = null, tint = ChateauGreen    )
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("Store", color = Color.Gray, fontSize = 14.sp)
                    Text("Insta Grocery Store", fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Place , contentDescription = null , tint = ChateauGreen)
                Spacer(Modifier.width(10.dp))
                Column {
                Text("Your place", color = Color.Gray, fontSize = 14.sp)
                Text("Queens Road London", fontWeight = FontWeight.Medium)
            }

            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
