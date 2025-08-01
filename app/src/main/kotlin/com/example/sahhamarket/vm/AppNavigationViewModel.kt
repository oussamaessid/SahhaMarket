package com.example.sahhamarket.vm

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.datastore.PreferenceStorage
import app.sahhamarket.compose.model.ActionResource
import app.sahhamarket.resources.R
import com.example.sahhamarket.navigation.RouteScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppNavigationViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage
) : ViewModel() {

    private var getAppNavigationStateJob: Job? = null

    private val _appNavigationState = MutableStateFlow(AppTopBarUiModel())
    val appNavigationState: StateFlow<AppTopBarUiModel> = _appNavigationState
        .stateIn(viewModelScope, SharingStarted.Lazily, AppTopBarUiModel())

    private val _startDestinationState = MutableStateFlow<RouteScreen?>(null)
    val startDestinationState: StateFlow<RouteScreen?> = _startDestinationState.asStateFlow()

    init {
        viewModelScope.launch {
            if (preferenceStorage.getIsFirstInstall().not()) {
                _startDestinationState.value = RouteScreen.Welcome
            } else {
                _startDestinationState.value = RouteScreen.Option
            }
        }
    }

    fun processAction(intent: UiAction) {
        when (intent) {
            is UiAction.OnRouteChanged -> {
                getAppNavigationStateJob?.cancel()
                getAppNavigationStateJob = viewModelScope.launch {
                    _appNavigationState.value = intent.route.toUiModel()
                }
            }
        }
    }

    private suspend fun RouteScreen.toUiModel() = when (this) {
        is RouteScreen.Location -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = false)

        RouteScreen.AddNewAddress -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = false)

        RouteScreen.Option -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = false)

        RouteScreen.Language -> AppTopBarUiModel(
            navigation = R.drawable.ic_back,
            titleResId = R.string.txt_language_title,
            isVisible = true
        )

        RouteScreen.Notification -> AppTopBarUiModel(
            navigation = R.drawable.ic_back,
            titleResId = R.string.txt_notification_title,
            isVisible = true
        )

        RouteScreen.Home -> AppTopBarUiModel(
            logoImg = R.drawable.ic_logo,
            actions = listOf(
                ActionResource.StringRes(resId = R.string.txt_lang_en),
                ActionResource.DrawableRes(resId = R.drawable.ic_bell)
            ),
            isVisible = true,
            isBottomBarVisible = true,
        )

        RouteScreen.Welcome -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = false)

        RouteScreen.OtpScreen -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = false)

        RouteScreen.Favorite -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = true)

        RouteScreen.Packages -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = true)

        RouteScreen.Cart -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = true)

        RouteScreen.Profile -> AppTopBarUiModel(isVisible = false, isBottomBarVisible = true)

        RouteScreen.ProductDetailScreen -> AppTopBarUiModel(
            navigation = R.drawable.ic_back,
            titleResId = R.string.txt_product_details_title,
            isVisible = true,
            isBottomBarVisible = false
        )

        RouteScreen.RecipesDetailScreen -> AppTopBarUiModel(
            navigation = R.drawable.ic_back,
            titleResId = R.string.txt_recipe_details_title,
            isVisible = true,
            isBottomBarVisible = false
        )

    }

    sealed interface UiAction {
        data class OnRouteChanged(val route: RouteScreen) : UiAction
    }
}

data class AppTopBarUiModel(
    val title: String? = null,
    @StringRes
    val titleResId: Int? = null,
    @DrawableRes
    val logoImg: Int? = null,
    @DrawableRes
    val navigation: Int? = null,
    val actions: List<ActionResource>? = null,
    val isVisible: Boolean = false,
    val isBottomBarVisible: Boolean = false
)