package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.ChateauGreen
import app.sahhamarket.compose.theme.OxfordBlue
import app.sahhamarket.compose.theme.SwansDown
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R

@Composable
fun InviteFriendsContent(
    onInviteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(color = SwansDown, shape = MaterialTheme.shapes.small)
            .paint(painter = painterResource(R.drawable.ic_bg_invite_frinds), contentScale = ContentScale.FillBounds)
            .paint(painter = painterResource(R.drawable.ic_boy), alignment = Alignment.TopEnd)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.6F)
                .padding(all = MaterialTheme.spacing.xs)
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = stringResource(R.string.txt_invite_friends),
                style = MaterialTheme.typography.displayMedium,
                color = OxfordBlue
            )

            Text(
                text = stringResource(R.string.txt_desc_invite_friends),
                style = MaterialTheme.typography.bodySmall,
                color = OxfordBlue,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.ms)
            )

            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ChateauGreen,
                    contentColor = White
                ),
                onClick = { onInviteClicked() }
            ) {
                Text(
                    text = stringResource(R.string.txt_btn_invite_friends),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}