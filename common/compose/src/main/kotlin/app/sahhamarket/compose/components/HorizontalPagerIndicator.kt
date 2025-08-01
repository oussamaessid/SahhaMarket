package app.sahhamarket.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.spacing


@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int,
    sizeIndicator: Dp = MaterialTheme.spacing.xs,
    selectedColor: Color = Black,
    unSelectedColor: Color = DustyGray,
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pageCount) { iteration ->
            val color = if (currentPage == iteration) selectedColor else unSelectedColor
            Box(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.xxxs)
                    .clip(CircleShape)
                    .background(color)
                    .size(sizeIndicator),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalPagerIndicatorPreview() {
    HorizontalPagerIndicator(
        modifier = Modifier.fillMaxWidth(),
        pageCount = 4,
        currentPage = 2,
    )
}
