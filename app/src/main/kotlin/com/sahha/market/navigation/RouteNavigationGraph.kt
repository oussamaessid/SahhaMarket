package com.sahha.market.navigation

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
import app.sahhamarket.presentation.category.ui.CategoryScreen
import app.sahhamarket.presentation.details.product.ui.ProductDetailScreen
import app.sahhamarket.presentation.details.recipes.ui.RecipesDetailScreen
import app.sahhamarket.presentation.favorite.ui.Favorite
import app.sahhamarket.presentation.home.ui.HomeScreen
import app.sahhamarket.presentation.location.ui.AddNewAddressScreen
import app.sahhamarket.presentation.location.ui.LocationScreen
import app.sahhamarket.presentation.onboarding.ui.OnboardingScreen
import app.sahhamarket.presentation.option.ui.OptionScreen
import app.sahhamarket.presentation.packages.ui.Packages
import app.sahhamarket.presentation.product.ui.ProductsScreen
import app.sahhamarket.presentation.profil.ui.Profile
import app.sahhamarket.presentation.subcategory.ui.SubCategoryScreen
import app.sahhamarket.presentation.welcome.WelcomeScreen
import com.sahha.market.vm.AppNavigationViewModel
import app.sahhamarket.presentation.trackorder.ui.OrderTrackingScreen

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
            onboardingScreen(navController = navController)
            welcomeScreen(navController = navController)
            recipesDetailScreen(navController = navController, appNavigationViewModel = appNavigationViewModel)
            languageScreen(navController = navController)
            notificationScreen(navController = navController)
            productScreen(navController = navController, appNavigationViewModel = appNavigationViewModel )
            subcategoryScreen(navController = navController, appNavigationViewModel = appNavigationViewModel)
            categoryScreen(navController = navController, appNavigationViewModel = appNavigationViewModel )
            trackorderscreen(navController = navController , appNavigationViewModel = appNavigationViewModel)
        }
    )
}

private fun NavGraphBuilder.productScreen(
    navController: NavHostController,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable<RouteScreen.ProductScreen> {
        ProductsScreen()
    }
}

private fun NavGraphBuilder.categoryScreen(
    navController: NavHostController,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable<RouteScreen.CategoryScreen> {
        CategoryScreen()
    }
}

private fun NavGraphBuilder.subcategoryScreen(
    navController: NavHostController,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable<RouteScreen.SubCategoryScreen> { backStackEntry ->
        SubCategoryScreen(
            onCategoryClicked = {
                navController.navigate(RouteScreen.ProductScreen)
            }
        )
    }
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
private fun NavGraphBuilder.trackorderscreen(
    navController: NavHostController,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable(
        route = "${RouteScreen.TrackOrder}",
    ) { navBackStackEntry ->
        appNavigationViewModel.processAction(AppNavigationViewModel.UiAction.OnRouteChanged(RouteScreen.TrackOrder))
        OrderTrackingScreen(navController = navController)
    }
}
private fun NavGraphBuilder.recipesDetailScreen(
    navController: NavHostController,
    appNavigationViewModel: AppNavigationViewModel
) {
    composable<RouteScreen.RecipesDetailScreen> {
        RecipesDetailScreen(
            onBackClick = { navController.popBackStack() }
        )    }
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
        Profile()
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
            modifier = Modifier,
            isCartExpanded = true,
            onDismiss = {
                navController.popBackStack()
            }
        )
    }
}

private fun NavGraphBuilder.packages(navController: NavHostController) {
    composable<RouteScreen.Packages> {
        Packages()
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
        HomeScreen(
            goToRecipesDetailScreen = {
                navController.navigate(RouteScreen.RecipesDetailScreen)
            },
            goToProductDetailScreen = { productId ->
                navController.navigate(RouteScreen.ProductDetailScreen(productId = productId))
            },
            goToCategoryScreen = {
                navController.navigate(RouteScreen.CategoryScreen)
            },
            goToSubcategoryScreen = { categoryId ->
                navController.navigate(RouteScreen.SubCategoryScreen(categoryId = categoryId))
            },
            goToProductsScreen = {
                navController.navigate(RouteScreen.ProductScreen)
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
    composable(
        route = OTP_SCREEN_ROUTE_WITH_ARG,
        arguments = listOf(navArgument("phone") { defaultValue = "" })
    ) { navBackStackEntry ->
        val phone = navBackStackEntry.arguments?.getString("phone") ?: ""

        OtpScreen(
            phone = phone,
            onOtpVerified = {
                navController.navigate(RouteScreen.Location)
            }
        )
    }
}



private fun NavGraphBuilder.optionScreen(navController: NavHostController) {
    composable<RouteScreen.Option> {
        OptionScreen(
            goToOtpScreen = { phone ->
                navController.navigate(RouteScreen.OtpScreen.withPhone(phone))
            }
        )
    }
}



private fun NavGraphBuilder.welcomeScreen(navController: NavHostController) {
    composable<RouteScreen.Welcome> {
        WelcomeScreen{
            navController.navigate(RouteScreen.Onboarding)
        }
    }
}

private fun NavGraphBuilder.onboardingScreen(navController: NavHostController) {
    composable<RouteScreen.Onboarding> {
        OnboardingScreen {
            navController.navigate(RouteScreen.Option)
        }
    }
}