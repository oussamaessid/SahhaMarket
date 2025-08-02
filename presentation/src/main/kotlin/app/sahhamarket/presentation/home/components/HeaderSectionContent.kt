package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R

@Composable
fun HeaderSectionContent(
    title: String,
    modifier: Modifier = Modifier,
    onSeeAllClicked: (() -> Unit)? = null,
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
            modifier = Modifier.weight(1f)
        )

        Text(
            text = stringResource(R.string.txt_see_all),
            style = MaterialTheme.typography.bodySmall,
            color = FountainBlue,
            modifier = Modifier.clickable {
                onSeeAllClicked?.invoke()
            }
        )
    }
}