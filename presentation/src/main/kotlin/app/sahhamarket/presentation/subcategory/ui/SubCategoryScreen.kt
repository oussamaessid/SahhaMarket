package app.sahhamarket.presentation.subcategory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.presentation.home.components.SearchBarContent
import app.sahhamarket.presentation.home.model.SubCategoryUiModel
import app.sahhamarket.presentation.subcategory.components.SubCategoryContent
import app.sahhamarket.presentation.subcategory.vm.SubCategoryViewModel
import app.sahhamarket.resources.R

@Composable
fun SubCategoryScreen(
    viewModel: SubCategoryViewModel = hiltViewModel(),
    onCategoryClicked: (Long) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SubCategoryViewModel.UiEvent.NavigateToSubCategoryDetail -> {
                    onCategoryClicked(event.subCategoryId)
                }
            }
        }
    }

    when (val subCategoriesState = uiState.subCategories) {
        is SubCategoryUiModel.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is SubCategoryUiModel.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.txt_error_loading_subcategories),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        is SubCategoryUiModel.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(MaterialTheme.spacing.s),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ms),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.ms),
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    SearchBarContent(
                        onSearch = {  },
                        modifier = Modifier.padding(vertical = MaterialTheme.spacing.xs)
                    )
                }

                items(subCategoriesState.subCategories) { subCategory ->
                    SubCategoryContent(
                        subCategory = subCategory,
                        isSelected = false,
                        modifier = Modifier.clickable {
                            // Use ViewModel action instead of direct callback
                            viewModel.processAction(
                                SubCategoryViewModel.UiAction.NavigateToSubCategoryDetail(subCategory.id)
                            )
                        }
                    )
                }
            }
        }
    }
}