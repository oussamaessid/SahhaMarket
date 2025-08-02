package app.sahhamarket.presentation.category.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.usecase.categories.GetCategoriesUseCase
import app.sahhamarket.presentation.home.model.CategoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        processAction(UiAction.LoadCategories)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.LoadCategories -> loadCategories()
                is UiAction.NavigateToCategoryDetail -> {
                    _uiEvent.emit(UiEvent.NavigateToCategoryDetail(action.categoryId))
                }
            }
        }
    }

    private suspend fun loadCategories() {
        _uiState.update { it.copy(categories = CategoryUiModel.Loading) }
        _uiState.update { state ->
            state.copy(
                categories = getCategoriesUseCase().fold(
                    onSuccess = { CategoryUiModel.Success(it) },
                    onFailure = { CategoryUiModel.Error }
                )
            )
        }
    }

    sealed interface UiAction {
        data object LoadCategories : UiAction
        data class NavigateToCategoryDetail(val categoryId: Long) : UiAction
    }

    sealed interface UiEvent {
        data class NavigateToCategoryDetail(val categoryId: Long) : UiEvent
    }

    data class CategoryUiState(
        val categories: CategoryUiModel = CategoryUiModel.Loading
    )
}