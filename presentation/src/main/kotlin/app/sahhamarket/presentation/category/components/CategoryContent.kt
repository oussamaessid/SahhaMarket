package app.sahhamarket.presentation.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Sunglo
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Category
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun CategoryContent(
    category: Category,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .aspectRatio(1f)
//            .border(
//                width = if (isSelected) MaterialTheme.spacing.xxxs else 0.dp,
//                color = Sunglo,
//                shape = MaterialTheme.shapes.medium
//            )
            .background(
                color = Color(category.colorBg),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.l)
                    .height(MaterialTheme.spacing.xl)
                    .align(Alignment.CenterHorizontally),
                colorFilter = ColorFilter.tint(color = White),
                contentDescription = null,
            )

            Text(
                text = category.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                color = White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.s,
                        vertical = MaterialTheme.spacing.m
                    )
            )
        }
    }
}