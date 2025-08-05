package app.sahhamarket.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.sahhamarket.compose.theme.Black60
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.SubCategory

@Composable
fun SubCategoryContent(
    subCategory: SubCategory,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {

    val (style, color) = if (isSelected) {
        Pair(MaterialTheme.typography.titleMedium, FountainBlue)
    } else {
        Pair(MaterialTheme.typography.bodyLarge, Black60)
    }
    Column(modifier = modifier.width(IntrinsicSize.Max)) {
        Text(
            text = subCategory.name,
            style = style,
            color = color
        )

        if (isSelected) {
            HorizontalDivider(
                thickness = MaterialTheme.spacing.xxxs,
                color = FountainBlue,
                modifier = Modifier.padding(top = MaterialTheme.spacing.xs)
            )
        }
    }

}