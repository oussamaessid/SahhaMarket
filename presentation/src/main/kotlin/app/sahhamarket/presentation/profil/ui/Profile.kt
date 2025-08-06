package app.sahhamarket.presentation.profil.ui


import android.annotation.SuppressLint
import app.sahhamarket.compose.theme.ChateauGreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import app.sahhamarket.resources.R
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.Alto
import app.sahhamarket.compose.theme.softred
import app.sahhamarket.presentation.home.vm.HomeViewModel
import app.sahhamarket.presentation.profil.components.ExpandableProfileSettingItem
import app.sahhamarket.presentation.profil.components.IconType
import app.sahhamarket.presentation.profil.components.ProfileStatCard
import app.sahhamarket.presentation.profil.vm.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun Profile(
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state by viewModel.stateProfile.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        TopAppBar(
            title = { Text("Profile", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },

            )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .border(5.dp, Color(0x80D6EEDD), RoundedCornerShape(12.dp))
                    .padding(8.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Card(
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(80.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFC8EDD9))
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .padding(2.dp)
                                    .fillMaxSize()
                                    .offset(y = 12.dp),
                                tint = ChateauGreen,


                                )
                        }
                        Text(
                            text = state.user?.name ?: "",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .size(48.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Icon(
                                    Icons.Default.Settings,
                                    contentDescription = "Settings",
                                    Modifier.size(28.dp)    ,
                                    tint = ChateauGreen
                                )
                            }
                        }


                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        ProfileStatCard("${state.user?.stats?.balance ?: "0"} SAR", painterResource(id =R.drawable.wallet ))
                        ProfileStatCard("${state.user?.stats?.points ?: "0"} points", painterResource(id = R.drawable.card))
                        ProfileStatCard("${state.user?.stats?.purchases ?: "0"} purchases", painterResource(id = R.drawable.basket))
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ){
                    Image(painter = painterResource(id = R.drawable.route), contentDescription = null , Modifier.size(58.dp))
                    Spacer(modifier = Modifier.width(25.dp))
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Order ID #12365236")
                        Text("10 items, est time 1 hr", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = ChateauGreen)
                        ) {
                            Text("Track Now",color = Color.White)
                        }

                    }
                    Text(text = "" , modifier = Modifier.weight(1f))
                }
            }
        }

        ExpandableProfileSettingItem("Language and Country", IconType.PainterIcon(painterResource(R.drawable.outline_language_24))) {
            Row(Modifier
                .background(
                    ChateauGreen,
                    RoundedCornerShape(12.dp)
                )
                .fillMaxWidth()
                .clickable {
                    {
                        //:TODO add change language
                    }
                }
                .padding(8.dp)

                ,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.uk), contentDescription = null, modifier = Modifier.fillMaxHeight())
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "English")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(Modifier
                .background(
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
                .fillMaxWidth()
                .clickable {
                    //:TODO add change language
                }
                .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.saudi), contentDescription = null, modifier = Modifier.fillMaxHeight())
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Arabic")
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        ExpandableProfileSettingItem("Push Notifications", IconType.Vector(Icons.Default.Notifications), {
            Text(text ="push notifications content")
        })
        Spacer(modifier = Modifier.height(4.dp))
        ExpandableProfileSettingItem("Payment Methods", IconType.PainterIcon(painterResource(R.drawable.baseline_account_balance_wallet_24)), {
            Text(text ="payment method content")
        })
        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text("Invite friends", style = MaterialTheme.typography.titleLarge)
            Text(
                "You get 15 SAR credit for every friend you invite on Flink.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ChateauGreen)
            ) {
                Text("Invite Friend",color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        Divider()
        Spacer(modifier = Modifier.height(12.dp))
        Text("Legal", style = MaterialTheme.typography.titleMedium , modifier = Modifier.padding(start = 14.dp))
        ExpandableProfileSettingItem("Privacy Policy", IconType.PainterIcon(painterResource(R.drawable.baseline_work_24)), {
            Text(text ="waaa")
        })
        ExpandableProfileSettingItem("Terms and Conditions", IconType.PainterIcon(painterResource(R.drawable.baseline_indeterminate_check_box_24)), {
            Text(text ="waaa")
        })
        Spacer(modifier = Modifier.height(12.dp))
        Text("Support", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(start = 14.dp))

        ExpandableProfileSettingItem("FAQ", IconType.PainterIcon(painterResource(R.drawable.outline_person_raised_hand_24)),{
            Text(text ="waaa")
        })
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier.fillMaxWidth().height(80.dp).padding(12.dp).clickable {
                //:TODO add logout
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                Modifier.fillMaxSize().background(softred).padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = null, modifier = Modifier.size(24.dp) , tint = Alto)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Logout", style = MaterialTheme.typography.bodyMedium , color = Alto)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}