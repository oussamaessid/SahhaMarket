package app.sahhamarket.presentation.welcome

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.TexasRose
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.compose.util.dpToPxF
import app.sahhamarket.resources.R

@RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR2)
@Composable
fun WelcomeScreen(goToOnboardingScreen: () -> Unit) {
    val screenHeightPx = LocalConfiguration.current.screenHeightDp.dpToPxF
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(TexasRose.copy(alpha = .2f), White),
                    start = Offset(0f, 0f),
                    end = Offset(0f, screenHeightPx / 2.5F)
                ),
            )
            .paint(painter = painterResource(R.drawable.ic_bg_welcome))
            .padding(MaterialTheme.spacing.l),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.txt_title_welcome),
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            color = Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.l,
                    bottom = MaterialTheme.spacing.m,
                )
        )

        Text(
            text = stringResource(R.string.txt_sub_title_welcome),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
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
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = FountainBlue,
                contentColor = White
            ),
            onClick = goToOnboardingScreen
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
