@file:OptIn(ExperimentalMaterial3Api::class)

package app.sahhamarket.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import app.sahhamarket.compose.model.ActionResource
import app.sahhamarket.compose.model.TopBarIcon
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.White

@Composable
fun TopBar(
    title: String?,
    logoImg: Int?,
    modifier: Modifier = Modifier,
    navigationIcon: TopBarIcon? = null,
    actionIcons: List<TopBarIcon>? = null,
    windowInsets: WindowInsets? = null,
) {
    TopAppBar(
        title = {
            if (!title.isNullOrEmpty()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Black,
                    modifier = Modifier.semantics { heading() },
                )
            } else if (logoImg != null) {
                Image(painter = painterResource(logoImg), contentDescription = null)
            }
        },
        navigationIcon = {
            navigationIcon?.also { icon ->
                IconButton(onClick = icon.action) {
                    if (icon.icon is ActionResource.DrawableRes) {
                        Icon(
                            imageVector = ImageVector.vectorResource(icon.icon.resId),
                            contentDescription = null,
                            tint = Black
                        )
                    }
                }
            }
        },
        actions = {
            actionIcons?.forEach { actionIcon ->
                IconButton(onClick = actionIcon.action) {
                    when (val icon = actionIcon.icon) {
                        is ActionResource.DrawableRes -> {
                            Icon(
                                imageVector = ImageVector.vectorResource(icon.resId),
                                contentDescription = null,
                            )
                        }

                        is ActionResource.StringRes -> {
                            Text(
                                text = stringResource(icon.resId),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = White,
            scrolledContainerColor = White,
            navigationIconContentColor = FountainBlue,
            titleContentColor = FountainBlue,
            actionIconContentColor = Black,
        ),
        windowInsets = windowInsets ?: TopAppBarDefaults.windowInsets,
        modifier = modifier,
    )
}