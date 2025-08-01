package com.example.sahhamarket.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import app.sahhamarket.compose.theme.TexasRose
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R
import com.example.sahhamarket.navigation.BottomNavigation
import com.example.sahhamarket.navigation.RouteNavigationGraph
import com.example.sahhamarket.navigation.RouteScreen
import com.example.sahhamarket.navigation.toRoute
import com.example.sahhamarket.vm.AppNavigationViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    appNavigationViewModel: AppNavigationViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    backStackEntry?.toRoute(RouteScreen.serializersBySealedClass)?.also { route ->
        appNavigationViewModel.processAction(
            AppNavigationViewModel.UiAction.OnRouteChanged(route),
        )
    }

    val appNavigationUiState by appNavigationViewModel.appNavigationState.collectAsStateWithLifecycle()
    val destinationState by appNavigationViewModel.startDestinationState.collectAsStateWithLifecycle()
    val startDestination = destinationState

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
                Box {
                    BottomAppBar(
                        containerColor = White,
                        tonalElevation = MaterialTheme.spacing.xs,
                    ) {
                        BottomNavigation.entries.forEachIndexed { index, item ->
                            if (index == 2) {
                                Spacer(modifier = Modifier.weight(1f))
                            } else {
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
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(item.icon),
                                        contentDescription = null,
                                        tint = if (selectedIndex == index) FountainBlue else DustyGray
                                    )
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                    ) {
                        FloatingActionButton(
                            onClick = { selectedIndex = 2 },
                            containerColor = FountainBlue,
                            shape = CircleShape,
                            elevation = FloatingActionButtonDefaults.elevation(MaterialTheme.spacing.xs)
                        ) {
                            Box {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_shopping_cart),
                                    contentDescription = "Cart",
                                    tint = White
                                )
                                Box(
                                    modifier = Modifier
                                        .size(MaterialTheme.spacing.xs)
                                        .background(color = TexasRose, shape = CircleShape)
                                        .align(Alignment.TopEnd)
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
}