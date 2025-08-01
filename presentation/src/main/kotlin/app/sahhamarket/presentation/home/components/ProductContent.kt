package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.sahhamarket.compose.theme.Alabaster
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.Mercury
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.Status
import app.sahhamarket.resources.R
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ProductContent(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(128.dp)
            .height(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
                .background(
                    color = Alabaster,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                modifier = Modifier
                    .padding(all = MaterialTheme.spacing.ms)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentDescription = null,
            )

            if (product.status == Status.UNAVAILABLE) {
                Text(
                    text = stringResource(R.string.txt_product_back_soon),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .background(
                            color = Mercury,
                            shape = RoundedCornerShape(
                                topStart = MaterialTheme.spacing.xs,
                                topEnd = MaterialTheme.spacing.xs
                            )
                        )
                        .padding(all = MaterialTheme.spacing.xxxs)
                )
            }

            FloatingActionButton(
                shape = MaterialTheme.shapes.small,
                elevation = FloatingActionButtonDefaults.elevation(MaterialTheme.spacing.unit),
                containerColor = White,
                contentColor = FountainBlue,
                modifier = Modifier
                    .padding(all = MaterialTheme.spacing.ms)
                    .align(Alignment.BottomEnd)
                    .size(MaterialTheme.spacing.l),
                onClick = {}
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.padding(MaterialTheme.spacing.xxs)
                )
            }
        }

        Text(
            text = stringResource(R.string.txt_price).format(product.price, product.currency),
            style = MaterialTheme.typography.bodyMedium,
            color = FountainBlue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xxs)
        )

        Text(
            text = product.title,
            style = MaterialTheme.typography.bodyLarge,
            color = Black,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xxs)
        )

        Text(
            text = product.subTitle,
            style = MaterialTheme.typography.labelMedium,
            color = Black60,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.xxs)
        )
    }
}