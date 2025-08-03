package app.sahhamarket.presentation.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.text.style.TextAlign
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
    Column(
        modifier = modifier
            .aspectRatio(1F)
            .background(
                color = Color(category.colorBg).copy(alpha = .3F),
                shape = MaterialTheme.shapes.medium
            )
            .border(
                width = MaterialTheme.spacing.unit,
                color = Color(category.colorBg).copy(alpha = .7F),
                shape = MaterialTheme.shapes.medium
            )
            .padding(all = MaterialTheme.spacing.s),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            modifier = Modifier
                .size(MaterialTheme.spacing.xxxl)
                .weight(1F),
            contentScale = ContentScale.Fit,
            contentDescription = category.title,
        )

        Text(
            text = category.title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = LunarGreen,
            textAlign = TextAlign.Center,
        )
    }
}