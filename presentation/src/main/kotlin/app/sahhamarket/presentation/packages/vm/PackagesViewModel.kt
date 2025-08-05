package app.sahhamarket.presentation.packages.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.Package
import app.sahhamarket.domain.usecase.packages.GetPackagesUseCase
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
class PackagesViewModel @Inject constructor(
    private val getPackagesUseCase: GetPackagesUseCase
) : ViewModel() {

    private val _statePackages: MutableStateFlow<UiState> = MutableStateFlow(
        UiState(
            weeklyPackages = emptyList(),
            alkhirPackages = emptyList(),
            lowCaloriePackages = emptyList(),
            zerdaPackages = emptyList(),
            isLoading = true
        )
    )
    val statePackages: StateFlow<UiState> = _statePackages.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        processAction(UiAction.InitData)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.InitData -> initData()
            }
        }
    }

    private suspend fun initData() {
        try {
            val weeklyPackages = getPackagesUseCase.getWeeklyPackages()
            val alkhirPackages = getPackagesUseCase.getAlkhirPackages()
            val lowCaloriePackages = getPackagesUseCase.getLowCaloriePackages()
            val zerdaPackages = getPackagesUseCase.getZerdaPackages()

            _statePackages.update {
                it.copy(
                    weeklyPackages = weeklyPackages,
                    alkhirPackages = alkhirPackages,
                    lowCaloriePackages = lowCaloriePackages,
                    zerdaPackages = zerdaPackages,
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            _uiEvent.emit(UiEvent.ShowError(e.message ?: "Failed to load packages"))
        }
    }

    sealed interface UiAction {
        data object InitData : UiAction
    }

    sealed interface UiEvent {
        data class NavigateToPackageDetails(val packageId: Int) : UiEvent
        data class ShowError(val message: String) : UiEvent
    }

    data class UiState(
        val weeklyPackages: List<Package> = emptyList(),
        val alkhirPackages: List<Package> = emptyList(),
        val lowCaloriePackages: List<Package> = emptyList(),
        val zerdaPackages: List<Package> = emptyList(),
        val isLoading: Boolean = true
    )
}