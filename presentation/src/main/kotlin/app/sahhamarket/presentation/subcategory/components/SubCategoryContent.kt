package app.sahhamarket.presentation.subcategory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.SubCategory

@Composable
fun SubCategoryContent(
    subCategory: SubCategory,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(MaterialTheme.spacing.xs))
            .background(
                if (isSelected) Color.Green.copy(alpha = 0.1f) else Color.LightGray.copy(
                    alpha = 0.3f
                )
            )
            .padding(MaterialTheme.spacing.s),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = subCategory.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}