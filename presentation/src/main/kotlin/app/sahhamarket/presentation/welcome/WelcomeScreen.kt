package app.sahhamarket.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.TexasRose
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.resources.R

@Composable
fun WelcomeScreen(goToOptionScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.l),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.welcome_illustration),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = MaterialTheme.spacing.l)
        )

        Text(
            text = stringResource(R.string.txt_title_welcome),
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            color = TexasRose,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.l,
                    bottom = MaterialTheme.spacing.s,
                )
        )

        Text(
            text = stringResource(R.string.txt_sub_title_welcome),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            maxLines = 3,
            minLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = DustyGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.l,
                    end = MaterialTheme.spacing.l,
                    bottom = MaterialTheme.spacing.xl
                )
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.l,
                    vertical = MaterialTheme.spacing.s,
                ),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = FountainBlue,
                contentColor = White
            ),
            onClick = goToOptionScreen
        ) {
            Text(
                text = stringResource(R.string.txt_btn_get_started),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.xs)
            )
        }
    }
}
