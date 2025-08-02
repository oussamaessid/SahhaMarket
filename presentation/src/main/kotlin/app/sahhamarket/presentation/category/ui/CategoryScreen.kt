package app.sahhamarket.presentation.category.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.presentation.category.components.CategoryContent
import app.sahhamarket.presentation.category.vm.CategoryViewModel
import app.sahhamarket.presentation.home.model.CategoryUiModel

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onCategoryClicked: (Long) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    return when (val categoriesData = state.categories) {
        is CategoryUiModel.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading categories...")
            }
        }

        is CategoryUiModel.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text("Error loading categories")
            }
        }

        is CategoryUiModel.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(MaterialTheme.spacing.m),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.m),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.m),
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
            ) {
                items(categoriesData.categories) { category ->
                    CategoryContent(
                        category = category,
                        isSelected = false,
                        modifier = Modifier.clickable {
                            onCategoryClicked(category.id)
                        }
                    )
                }
            }
        }
    }
}