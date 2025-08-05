package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import app.sahhamarket.compose.theme.LunarGreen
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Category
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun CategoryContent(
    category: Category,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = Color(category.colorBg).copy(alpha = .3F),
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = MaterialTheme.spacing.s),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            modifier = Modifier
                .size(MaterialTheme.spacing.xxxl),
            contentScale = ContentScale.Fit,
            contentDescription = category.title,
        )

        Text(
            text = category.title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = LunarGreen,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.s)
        )
    }
}