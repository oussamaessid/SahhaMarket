@file:OptIn(ExperimentalPermissionsApi::class)

package app.sahhamarket.presentation.location.ui

import android.Manifest
import android.R.attr.name
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.Alto
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.ShipRed
import app.sahhamarket.compose.theme.TexasRose
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Address
import app.sahhamarket.presentation.location.vm.LocationViewModel
import app.sahhamarket.resources.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("PermissionLaunchedDuringComposition")
@Composable
fun LocationScreen(
    viewModel: LocationViewModel = hiltViewModel(),
    goToAddNewAddressScreen: () -> Unit,
    goToHomeScreen: () -> Unit,
) {
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val state by viewModel.stateLocation.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is LocationViewModel.UiEvent.GoToAddNewAddressScreen -> {
                    goToAddNewAddressScreen()
                }

                is LocationViewModel.UiEvent.ShowPermissionDialog -> {
                    permissionState.launchPermissionRequest()
                }

                is LocationViewModel.UiEvent.GoToHomeScreen -> {
                    goToHomeScreen()
                }
            }
        }
    }

    LocationContent(
        state = state,
        onClickAddNewAddress = {
            viewModel.processAction(
                LocationViewModel.UiAction.OnCheckLocationPermission(
                    permissionState.status.isGranted
                )
            )
        },
        goToHomeScreen = { goToHomeScreen() }
    )
}

@Composable
fun LocationContent(
    state: LocationViewModel.UiState,
    onClickAddNewAddress: () -> Unit,
    goToHomeScreen: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        LocationHeader(
            currentLocation = state.defaultAddress ?: stringResource(R.string.txt_location_not_set),
        )

        if (state.deliveryLocationList.isEmpty()) {
            Spacer(modifier = Modifier.weight(1F))

            Image(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "Welcome Image",
                modifier = Modifier
                    .size(MaterialTheme.spacing.xl)
            )

            Text(
                text = stringResource(R.string.txt_add_your_location),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = TexasRose,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.xs, bottom = MaterialTheme.spacing.m)
            )

            Text(
                text = stringResource(R.string.txt_location_description),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.m)
            )

            Spacer(modifier = Modifier.weight(1F))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(top = MaterialTheme.spacing.s)
            ) {
                items(state.deliveryLocationList) { address ->
                    AddressItem(
                        address = address,
                        modifier = Modifier.clickable {
                            goToHomeScreen()
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.l)
                            .background(color = Alto)
                    )
                }
            }
        }

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
                onClickAddNewAddress()
            }
        ) {
            Text(
                text = stringResource(R.string.txt_btn_add_location),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.xs)
            )
        }
    }
}


@Composable
fun LocationHeader(
    currentLocation: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.s, vertical = MaterialTheme.spacing.s),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(color = FountainBlue, shape = MaterialTheme.shapes.small)
                .size(MaterialTheme.spacing.l)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_location),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(MaterialTheme.spacing.xxs)
            )
        }

        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.s)) {
            Text(
                text = stringResource(R.string.txt_current_location),
                style = MaterialTheme.typography.bodyMedium,
                color = TexasRose,
            )

            Text(
                text = currentLocation,
                style = MaterialTheme.typography.bodyLarge,
                color = Black
            )
        }

        Spacer(modifier = Modifier.weight(1F))
    }
}

@Composable
fun AddressItem(
    address: Address,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.ms,
                horizontal = MaterialTheme.spacing.l
            )
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = address.name.toString(),
                style = MaterialTheme.typography.headlineLarge,
                color = Black
            )
            Text(
                text = address.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier
                .background(color = Black60, shape = MaterialTheme.shapes.extraLarge)
                .size(MaterialTheme.spacing.ml)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                contentDescription = "Edit address",
                tint = White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(MaterialTheme.spacing.xxs)
            )
        }

        Spacer(modifier = Modifier.size(MaterialTheme.spacing.xs))

        Box(
            modifier = Modifier
                .background(color = ShipRed, shape = MaterialTheme.shapes.extraLarge)
                .size(MaterialTheme.spacing.ml)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                contentDescription = "Delete address",
                tint = White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(MaterialTheme.spacing.ms)
            )
        }
    }
}
