package app.sahhamarket.presentation.location.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.presentation.location.vm.AddNewAddressViewModel
import app.sahhamarket.resources.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun AddNewAddressScreen(
    viewModel: AddNewAddressViewModel = hiltViewModel(),
    goToLocationScreen:()->Unit,
) {
    val state by viewModel.stateAddNewAddress.collectAsStateWithLifecycle()

    AddNewAddressContent(
        state = state,
        onUpdateAddress = {
            viewModel.processAction(AddNewAddressViewModel.UiAction.OnUpdateAddress(it))
        },
        onConfirmAddress = {
            viewModel.processAction(AddNewAddressViewModel.UiAction.OnAddNewAddress)
            goToLocationScreen()
        }
    )
}

@Composable
fun AddNewAddressContent(
    state: AddNewAddressViewModel.UiState,
    onUpdateAddress: (LatLng) -> Unit,
    onConfirmAddress: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error : ${state.error}")
                }
            }

            state.latLng != null -> {
                Column {
                    GoogleMap(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F),
                        cameraPositionState = rememberCameraPositionState {
                            position = CameraPosition.fromLatLngZoom(
                                LatLng(
                                    state.latLng.latitude,
                                    state.latLng.longitude
                                ), 17f
                            )
                        },
                        properties = MapProperties(isMyLocationEnabled = true),
                        onMapClick = { latLng ->
                            onUpdateAddress(latLng)
                        }
                    ) {
                        Marker(
                            state = MarkerState(position = state.latLng),
                            title = "Vous êtes ici"
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = White,
                                shape = RoundedCornerShape(
                                    topStart = MaterialTheme.spacing.l,
                                    topEnd = MaterialTheme.spacing.l
                                )
                            )
                            .padding(
                                vertical = MaterialTheme.spacing.l,
                                horizontal = MaterialTheme.spacing.s
                            )
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.txt_select_location),
                                style = MaterialTheme.typography.headlineSmall,
                                color = Black,
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(
                                    top = MaterialTheme.spacing.ms,
                                    bottom = MaterialTheme.spacing.m,
                                )
                            )

                            Text(
                                text = state.address,
                                style = MaterialTheme.typography.titleMedium,
                                color = Black,
                                modifier = Modifier.padding(bottom = MaterialTheme.spacing.s)
                            )

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = MaterialTheme.spacing.l,
                                        vertical = MaterialTheme.spacing.s,
                                    ),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = FountainBlue,
                                    contentColor = White
                                ),
                                onClick = {
                                    onConfirmAddress()
                                }
                            ) {
                                Text(
                                    text = stringResource(R.string.txt_btn_confirm_address),
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.xs)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}