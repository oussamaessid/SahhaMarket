package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.CatskillWhite
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.OxfordBlue
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Receipt
import app.sahhamarket.resources.R
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun DailyReceiptContent(
    receipt: Receipt,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .width(197.dp)
            .height(IntrinsicSize.Max)
            .background(color = White, shape = MaterialTheme.shapes.small)
            .border(
                width = MaterialTheme.spacing.unit,
                color = CatskillWhite,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5 / 3F)
                .background(
                    color = White,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(receipt.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .clip(shape = MaterialTheme.shapes.small),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.ms)
        ) {
            Text(
                text = receipt.title,
                style = MaterialTheme.typography.bodySmall,
                color = OxfordBlue,
                maxLines = 3,
                minLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(end = MaterialTheme.spacing.xs)
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_save),
                contentDescription = null,
                tint = FountainBlue,
                modifier = Modifier.clickable { }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.ms)
        ) {
            Text(
                text = context.resources.getQuantityString(
                    R.plurals.txt_ingredients_count,
                    receipt.ingredients.size, receipt.ingredients.size
                ),
                style = MaterialTheme.typography.labelMedium,
                color = OxfordBlue,
                modifier = Modifier.padding(end = MaterialTheme.spacing.xs)
            )

            Box(
                modifier = Modifier
                    .background(color = Black, shape = CircleShape)
                    .size(MaterialTheme.spacing.xxxs)
            )

            Text(
                text = receipt.time,
                style = MaterialTheme.typography.labelMedium,
                color = OxfordBlue,
                modifier = Modifier.padding(start = MaterialTheme.spacing.xs)
            )
        }

        Text(
            text = stringResource(R.string.txt_price).format(receipt.price, receipt.currency),
            style = MaterialTheme.typography.headlineLarge,
            color = FountainBlue,
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.ms,
                bottom = MaterialTheme.spacing.ms)
        )

    }
}