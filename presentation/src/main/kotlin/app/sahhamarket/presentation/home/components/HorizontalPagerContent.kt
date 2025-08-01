package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.PromoCard
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

const val AUTO_SCROLL_DURATION = 3000L

@Composable
fun PromoCardContent(
    promoCard: PromoCard,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(MaterialTheme.spacing.s),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.spacing.xs)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .let { mod ->
                    if (promoCard.imageUrl == null && promoCard.imageRes == null) {
                        mod.background(Color.Gray)
                    } else {
                        mod.background(Color.Transparent)
                    }
                }
        ) {
            if (promoCard.imageUrl != null) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(promoCard.imageUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(MaterialTheme.spacing.s)),
                    contentScale = ContentScale.Crop
                )
            } else if (promoCard.imageRes != null) {
                Image(
                    painter = painterResource(id = android.R.drawable.picture_frame),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(MaterialTheme.spacing.s)),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .padding(MaterialTheme.spacing.l),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = promoCard.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.xs)
                )

                Text(
                    text = promoCard.discount,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.xs)
                )

                Text(
                    text = promoCard.description,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun HorizontalPagerContent(
    pageList: List<PromoCard>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pageList.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (isDragged.not()) {
        with(pagerState) {
            var currentPageKey by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = AUTO_SCROLL_DURATION)
                    val nextPage = (currentPage + 1).mod(pageCount)
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fill,
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.l),
        pageSpacing = MaterialTheme.spacing.s,
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .height(200.dp)
    ) { index ->
        PromoCardContent(
            promoCard = pageList[index],
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - index) + pagerState.currentPageOffsetFraction).absoluteValue

                    val transformation =
                        lerp(
                            start = 0.7f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    alpha = transformation
                    scaleY = transformation
                }
                .clickable { }
        )
    }
}