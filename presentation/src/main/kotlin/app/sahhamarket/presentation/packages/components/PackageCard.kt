package app.sahhamarket.presentation.packages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Alabaster
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.OxfordBlue
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Package
import app.sahhamarket.resources.R
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun PackageCard(
    packageItem: Package,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(130.dp)
            .height(IntrinsicSize.Max)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(
                    color = Alabaster,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(packageItem.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier
                    .padding(all = MaterialTheme.spacing.ms)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentDescription = packageItem.title,
                contentScale = ContentScale.Crop
            )

            when {
                packageItem.discount != null -> {
                    Text(
                        text = "-${packageItem.discount}%",
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .background(Color.Red, shape = RoundedCornerShape(3.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                            .align(Alignment.TopEnd)
                    )
                }

                packageItem.isDonate -> {
                    Text(
                        text = "DONATE",
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .background(Color.Red, shape = RoundedCornerShape(3.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                            .align(Alignment.TopEnd)
                    )
                }
            }
        }

        Text(
            text = stringResource(R.string.txt_price_package).format(
                packageItem.discountedPrice,
                stringResource(R.string.currency_sar)
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = FountainBlue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xxs)
        )

        if (packageItem.originalPrice.isNotEmpty()) {
            Text(
                text = stringResource(R.string.txt_price_package).format(
                    packageItem.originalPrice,
                    stringResource(R.string.currency_sar)
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = Black60,
                textDecoration = TextDecoration.LineThrough,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.xxs)
            )
        }

        Text(
            text = packageItem.title,
            style = MaterialTheme.typography.labelLarge,
            color = OxfordBlue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xxs)
        )

        Text(
            text = packageItem.subTitle,
            style = MaterialTheme.typography.labelLarge,
            color = OxfordBlue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xxs)
        )
    }
}