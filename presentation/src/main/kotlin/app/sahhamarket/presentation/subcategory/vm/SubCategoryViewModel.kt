package app.sahhamarket.presentation.subcategory.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.usecase.subCategories.GetSubCategoriesUseCase
import app.sahhamarket.presentation.home.model.SubCategoryUiModel
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
class SubCategoryViewModel @Inject constructor(
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryId: Long = savedStateHandle.get<Long>("categoryId") ?: 0L

    private val _uiState = MutableStateFlow(SubCategoryUiState())
    val uiState: StateFlow<SubCategoryUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        if (categoryId != 0L) {
            processAction(UiAction.LoadSubCategories(categoryId))
        } else {
            _uiState.update { it.copy(subCategories = SubCategoryUiModel.Error) }
        }
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.LoadSubCategories -> loadSubCategories(action.categoryId)
                is UiAction.NavigateToSubCategoryDetail -> {
                    _uiEvent.emit(UiEvent.NavigateToSubCategoryDetail(action.subCategoryId))
                }
            }
        }
    }

    private suspend fun loadSubCategories(categoryId: Long) {
        _uiState.update { it.copy(subCategories = SubCategoryUiModel.Loading) }
        _uiState.update { state ->
            state.copy(
                subCategories = getSubCategoriesUseCase(categoryId).fold(
                    onSuccess = { SubCategoryUiModel.Success(it) },
                    onFailure = { SubCategoryUiModel.Error }
                )
            )
        }
    }

    sealed interface UiAction {
        data class LoadSubCategories(val categoryId: Long) : UiAction
        data class NavigateToSubCategoryDetail(val subCategoryId: Long) : UiAction
    }

    sealed interface UiEvent {
        data class NavigateToSubCategoryDetail(val subCategoryId: Long) : UiEvent
    }

    data class SubCategoryUiState(
        val subCategories: SubCategoryUiModel = SubCategoryUiModel.Loading
    )
}