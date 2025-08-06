package com.sahha.market.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.sahhamarket.compose.components.TopBar
import app.sahhamarket.compose.model.ActionResource
import app.sahhamarket.compose.model.TopBarIcon
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.presentation.cart.ui.Cart
import com.sahha.market.navigation.BottomNavigation
import com.sahha.market.navigation.RouteScreen
import com.sahha.market.navigation.RouteNavigationGraph
import com.sahha.market.navigation.toRoute
import com.sahha.market.vm.AppNavigationViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    appNavigationViewModel: AppNavigationViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    backStackEntry?.toRoute(RouteScreen.Companion.serializersBySealedClass)?.also { route ->
        appNavigationViewModel.processAction(
            AppNavigationViewModel.UiAction.OnRouteChanged(route),
        )
    }

    val appNavigationUiState by appNavigationViewModel.appNavigationState.collectAsStateWithLifecycle()
    val destinationState by appNavigationViewModel.startDestinationState.collectAsStateWithLifecycle()
    val startDestination = destinationState
    var isCartShown by remember { mutableStateOf(false) }
    val (onActionClick, setOnActionClick) = remember { mutableStateOf<List<(() -> Unit)>?>(null) }
    val navigationIcon = appNavigationUiState.navigation?.let {
        TopBarIcon(
            icon = ActionResource.DrawableRes(resId = it),
            action = {
                navController.popBackStack()
            },
        )
    }
    val actionIcons = appNavigationUiState.actions?.mapIndexed { index, icon ->
        TopBarIcon(
            icon = icon,
            action = {
                onActionClick?.get(index)?.invoke()
            },
        )
    }

    Box(Modifier.fillMaxSize()) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                with(appNavigationUiState) {
                    if (isVisible) {
                        TopBar(
                            title = title ?: titleResId?.let { stringResource(id = it) }.orEmpty(),
                            logoImg = logoImg,
                            navigationIcon = navigationIcon,
                            actionIcons = actionIcons,
                        )
                    }
                }
            },
            bottomBar = {
                if (appNavigationUiState.isBottomBarVisible) {
                    var selectedIndex by remember { mutableIntStateOf(0) }
                    BottomAppBar(
                        containerColor = White,
                        tonalElevation = MaterialTheme.spacing.xs,
                    ) {
                        BottomNavigation.entries.forEachIndexed { index, item ->
                            IconButton(
                                onClick = {
                                    selectedIndex = index
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                    if (item.route == RouteScreen.Cart) {
                                        isCartShown = true
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(item.icon),
                                        contentDescription = item.route.toString(),
                                        tint = if (selectedIndex == index) FountainBlue else DustyGray
                                    )
                                    Text(
                                        text = item.displayName,
                                        fontSize = 10.sp,
                                        fontWeight = if (selectedIndex == index) FontWeight.Medium else FontWeight.Normal,
                                        color = if (selectedIndex == index) FountainBlue else DustyGray,
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->
            if (startDestination != null) {
                RouteNavigationGraph(
                    navController = navController,
                    startDestination = startDestination,
                    setOnActionClick = setOnActionClick,
                    modifier = Modifier.padding(innerPadding),
                )
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }

        if (isCartShown) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Cart(
                    navController = navController,
                    isCartExpanded = isCartShown,
                    onDismiss = { isCartShown = false }
                )
            }
        }
    }
}