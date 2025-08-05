package app.sahhamarket.presentation.packages.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.sahhamarket.presentation.packages.components.PackageCard
import app.sahhamarket.presentation.packages.vm.PackagesViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Packages(
    viewModel: PackagesViewModel = hiltViewModel(),
) {
    val state = viewModel.statePackages.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is PackagesViewModel.UiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }

                is PackagesViewModel.UiEvent.NavigateToPackageDetails -> TODO()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn {
                if (state.weeklyPackages.isNotEmpty()) {
                    item {
                        Section(title = "Weekly Packages") {
                            LazyRow {
                                items(state.weeklyPackages) { item ->
                                    PackageCard(
                                        packageItem = item,
                                        onClick = {  }
                                    )
                                }
                            }
                        }
                    }
                }

                if (state.alkhirPackages.isNotEmpty()) {
                    item {
                        Section(title = "Alkhir Packages") {
                            LazyRow {
                                items(state.alkhirPackages) { item ->
                                    PackageCard(
                                        packageItem = item,
                                        onClick = {  }
                                    )
                                }
                            }
                        }
                    }
                }

                if (state.lowCaloriePackages.isNotEmpty()) {
                    item {
                        Section(title = "Low Calorie Packages") {
                            LazyRow {
                                items(state.lowCaloriePackages) { item ->
                                    PackageCard(
                                        packageItem = item,
                                        onClick = {  }
                                    )
                                }
                            }
                        }
                    }
                }

                if (state.zerdaPackages.isNotEmpty()) {
                    item {
                        Section(title = "Zarda Packages") {
                            LazyRow {
                                items(state.zerdaPackages) { item ->
                                    PackageCard(
                                        packageItem = item,
                                        onClick = {  }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Section(title: String, content: @Composable () -> Unit) {
    Column {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        content()
    }
}