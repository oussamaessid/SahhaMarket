package com.example.sahhamarket.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import app.sahhamarket.presentation.auth.ui.OtpScreen
import app.sahhamarket.presentation.cart.ui.Cart
import app.sahhamarket.presentation.details.product.ui.ProductDetailScreen
import app.sahhamarket.presentation.details.recipes.ui.RecipesDetailScreen
import app.sahhamarket.presentation.favorite.ui.Favorite
import app.sahhamarket.presentation.home.ui.HomeScreen
import app.sahhamarket.presentation.location.ui.AddNewAddressScreen
import app.sahhamarket.presentation.location.ui.LocationScreen
import app.sahhamarket.presentation.option.ui.OptionScreen
import app.sahhamarket.presentation.packages.ui.Packages
import app.sahhamarket.presentation.profil.ui.Profile
import app.sahhamarket.presentation.welcome.ui.WelcomeScreen
import com.example.sahhamarket.vm.AppNavigationViewModel

@Composable
fun RouteNavigationGraph(
    navController: NavHostController,
    startDestination: RouteScreen,
    setOnActionClick: (List<() -> Unit>?) -> Unit,
    modifier: Modifier = Modifier,
    appNavigationViewModel: AppNavigationViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        builder = {
            productDetailScreen(navController = navController, appNavigationViewModel = appNavigationViewModel)
            profile(navController = navController)
            favorite(navController = navController)
            cart(navController = navController)
            packages(navController = navController)
            homeScreen(navController = navController, setOnActionClick = setOnActionClick, appNavigationViewModel = appNavigationViewModel)
            optionScreen(navController = navController)
            otpScreen(navController = navController)
            locationScreen(navController = navController)
            addNewAddressScreen(navController = navController)
            welcomeScreen(navController = navController)
            recipesDetailScreen(navController = navController, appNavigationViewModel = appNavigationViewModel)
            languageScreen(navController = navController)
            notificationScreen(navController = navController)
        }
    )
}

private fun NavGraphBuilder.languageScreen(navController: NavHostController) {
    composable<RouteScreen.Language> {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Language",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}

private fun NavGraphBuilder.notificationScreen(navController: NavHostController) {
    composable<RouteScreen.Notification> {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Notification",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}

private fun NavGraphBuilder.recipesDetailScreen(
    navController: NavHostController,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable(
        route = "${RouteScreen.RecipesDetailScreen}/{id}",
        arguments = listOf(navArgument("id") { })
    ) { navBackStackEntry ->
        val receiptId = navBackStackEntry.arguments?.getString("id")
        appNavigationViewModel.processAction(AppNavigationViewModel.UiAction.OnRouteChanged(RouteScreen.RecipesDetailScreen))
        RecipesDetailScreen(receiptId = receiptId, navController = navController)
    }
}

private fun NavGraphBuilder.productDetailScreen(
    navController: NavHostController,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable<RouteScreen.ProductDetailScreen> { backStackEntry ->
        val productId = backStackEntry.toRoute<RouteScreen.ProductDetailScreen>().productId
        ProductDetailScreen(productId = productId)
    }
}

private fun NavGraphBuilder.profile(navController: NavHostController) {
    composable<RouteScreen.Profile> {
        Profile(
            navController = navController,
            modifier = Modifier
        )
    }
}

private fun NavGraphBuilder.favorite(navController: NavHostController) {
    composable<RouteScreen.Favorite> {
        Favorite(
            navController = navController,
            modifier = Modifier
        )
    }
}

private fun NavGraphBuilder.cart(navController: NavHostController) {
    composable<RouteScreen.Cart> {
        Cart(
            navController = navController,
            modifier = Modifier
        )
    }
}

private fun NavGraphBuilder.packages(navController: NavHostController) {
    composable<RouteScreen.Packages> {
        Packages(
            onNavigateToDetails = { packageId ->
                navController.navigate("packageDetails/$packageId")
            }
        )
    }
}

private fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
    setOnActionClick: (List<() -> Unit>?) -> Unit,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable<RouteScreen.Home> {
        setOnActionClick(
            listOf(
                { navController.navigate(RouteScreen.Language) },
                { navController.navigate(RouteScreen.Notification) },
            )
        )
        appNavigationViewModel.processAction(AppNavigationViewModel.UiAction.OnRouteChanged(RouteScreen.Home))
        HomeScreen(
            goToRecipesDetailScreen = { id ->
                navController.navigate("${RouteScreen.RecipesDetailScreen}/$id") {
                    popUpTo(RouteScreen.Home) { inclusive = false }
                    launchSingleTop = true
                }
            },
            goToProductDetailScreen = { productId ->
                navController.navigate(RouteScreen.ProductDetailScreen(productId = productId))
            }
        )
    }
}

private fun NavGraphBuilder.addNewAddressScreen(navController: NavHostController) {
    composable<RouteScreen.AddNewAddress> {
        AddNewAddressScreen {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.locationScreen(navController: NavHostController) {
    composable<RouteScreen.Location> {
        LocationScreen(
            goToAddNewAddressScreen = {
                navController.navigate(RouteScreen.AddNewAddress)
            },
            goToHomeScreen = {
                navController.navigate(RouteScreen.Home)
            }
        )
    }
}

private fun NavGraphBuilder.otpScreen(navController: NavHostController) {
    composable<RouteScreen.OtpScreen> {
        OtpScreen {
            navController.navigate(RouteScreen.Location) {
                popUpTo(RouteScreen.OtpScreen) {
                    inclusive = true
                }
            }
        }
    }
}

private fun NavGraphBuilder.optionScreen(navController: NavHostController) {
    composable<RouteScreen.Option> {
        OptionScreen {
            navController.navigate(RouteScreen.OtpScreen)
        }
    }
}

private fun NavGraphBuilder.welcomeScreen(navController: NavHostController) {
    composable<RouteScreen.Welcome> {
        WelcomeScreen {
            navController.navigate(RouteScreen.Option)
        }
    }
}