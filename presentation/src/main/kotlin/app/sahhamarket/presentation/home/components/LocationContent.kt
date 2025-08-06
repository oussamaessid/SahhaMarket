package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Address
import app.sahhamarket.resources.R

@Composable
fun LocationContent(
    address: Address,
    onLocationClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onLocationClicked() }
            .padding(
                vertical = MaterialTheme.spacing.ms,
                horizontal = MaterialTheme.spacing.s,
            )
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

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xxxs),
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.s)
                .weight(1F)
        ) {
            Text(
                text = address.name ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = Black
            )

            Text(
                text = address.getStreetWithPostalCode(),
                style = MaterialTheme.typography.bodyLarge,
                color = Black
            )
        }

        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            modifier = Modifier.size(MaterialTheme.spacing.m)
        )
    }
}