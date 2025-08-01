package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R

@Composable
fun HeaderSectionContent(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.s)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = Black,
            modifier = Modifier.weight(1F)
        )

        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
            contentDescription = null
        )
    }
}