package com.sahha.market.navigation

import androidx.annotation.DrawableRes
import app.sahhamarket.resources.R
import kotlinx.serialization.Serializable

sealed interface RouteScreen {

    @Serializable
    data object Onboarding : RouteScreen

    @Serializable
    data object Welcome : RouteScreen

    @Serializable
    data object Option : RouteScreen

    @Serializable
    data object OtpScreen : RouteScreen

    @Serializable
    data object Location : RouteScreen

    @Serializable
    data object AddNewAddress : RouteScreen

    @Serializable
    data object Home : RouteScreen

    @Serializable
    data object Profile : RouteScreen

    @Serializable
    data object Favorite : RouteScreen

    @Serializable
    data object Cart : RouteScreen

    @Serializable
    data object Packages : RouteScreen

    @Serializable
    data class ProductDetailScreen(
        val productId: Long
    ) : RouteScreen

    @Serializable
    data object RecipesDetailScreen : RouteScreen

    @Serializable
    data object Language : RouteScreen

    @Serializable
    data object Notification : RouteScreen

    @Serializable
    data object CategoryScreen : RouteScreen

    @Serializable
    data class SubCategoryScreen(
        val categoryId: Long
    ) : RouteScreen

    @Serializable
    data object ProductScreen : RouteScreen

    @Serializable
    data object TrackOrder : RouteScreen

    companion object {
        val serializersBySealedClass by lazy {
            mapOf(
                Onboarding::class to Onboarding.serializer(),
                Welcome::class to Welcome.serializer(),
                Option::class to Option.serializer(),
                OtpScreen::class to OtpScreen.serializer(),
                Location::class to Location.serializer(),
                AddNewAddress::class to AddNewAddress.serializer(),
                Home::class to Home.serializer(),
                Profile::class to Profile.serializer(),
                Favorite::class to Favorite.serializer(),
                Cart::class to Cart.serializer(),
                Packages::class to Packages.serializer(),
                ProductDetailScreen::class to ProductDetailScreen.serializer(),
                RecipesDetailScreen::class to RecipesDetailScreen.serializer(),
                Language::class to Language.serializer(),
                Notification::class to Notification.serializer(),
                CategoryScreen::class to CategoryScreen.serializer(),
                SubCategoryScreen::class to SubCategoryScreen.serializer(),
                ProductScreen::class to ProductScreen.serializer(),
                TrackOrder::class to TrackOrder.serializer(),
            )
        }
    }
}

const val OTP_SCREEN_BASE_ROUTE = "otp_screen"
const val OTP_SCREEN_ROUTE_WITH_ARG = "$OTP_SCREEN_BASE_ROUTE/{phone}"

fun RouteScreen.OtpScreen.withPhone(phone: String): String {
    return "$OTP_SCREEN_BASE_ROUTE/$phone"
}

enum class BottomNavigation(
    @DrawableRes val icon: Int,
    val route: RouteScreen,
    val displayName: String
) {
    HOME(
        icon = R.drawable.ic_home,
        route = RouteScreen.Home,
        displayName = "Home"
    ),
    PACKAGE(
        icon = R.drawable.ic_packages,
        route = RouteScreen.Packages,
        displayName = "Dashboard "
    ),
    FAVORITE(
        icon = R.drawable.ic_favorite,
        route = RouteScreen.Notification,
        displayName = "Saved"
    ),
    CART(
        icon = R.drawable.ic_shopping_cart,
        route = RouteScreen.Cart,
        displayName = "Cart"
    ),
    PROFILE(
        icon = R.drawable.ic_profile,
        route = RouteScreen.Profile,
        displayName = "Account"
    )
}

