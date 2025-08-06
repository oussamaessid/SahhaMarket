package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.LightYellow
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.compose.components.HorizontalPagerIndicator
import app.sahhamarket.domain.model.PromoCard
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

const val AUTO_SCROLL_DURATION = 3000L

@Composable
fun PromoCardContent(
    promoCard: PromoCard,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(MaterialTheme.spacing.s),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.spacing.xs)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(FountainBlue)
                .clip(RoundedCornerShape(MaterialTheme.spacing.s))
        ) {
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .offset(x = 100.dp, y = 0.dp)
                    .background(
                        color = LightYellow,
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    .align(Alignment.CenterEnd)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = MaterialTheme.spacing.l, bottom = MaterialTheme.spacing.s,
                        start = MaterialTheme.spacing.l
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = promoCard.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.xs)
                )

                Text(
                    text = promoCard.discount,
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.xs)
                )

                Text(
                    text = promoCard.description,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.xs)
                )

                Button(
                    onClick = { /* Handle click */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightYellow
                    ),
                    shape = RoundedCornerShape(MaterialTheme.spacing.xm),
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.xs)
                ) {
                    Text(
                        text = "See Detail",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Image(
                painter = rememberAsyncImagePainter("file:///android_asset/ic_promocard.png"),
                contentDescription = promoCard.title,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.s)),
                contentScale = ContentScale.Crop
            )

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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.l),
            pageSpacing = MaterialTheme.spacing.s,
            modifier = Modifier
                .fillMaxWidth()
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
        HorizontalPagerIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.s),
            pageCount = pageList.size,
            currentPage = pagerState.currentPage,
        )
    }
}