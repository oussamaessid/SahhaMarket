package app.sahhamarket.presentation.onboarding.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.datastore.PreferenceStorage
import app.sahhamarket.domain.model.OnboardingInfo
import app.sahhamarket.domain.usecase.onboarding.GetOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getWelcomeUseCase: GetOnboardingUseCase,
    private val preferenceStorage: PreferenceStorage,
) : ViewModel() {
    private val welcomeList
        get() = getWelcomeUseCase()

    private val _stateWelcome: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(welcomeInfoList = welcomeList))
    val stateWelcome: StateFlow<UiState> = _stateWelcome.asStateFlow()

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                is UiAction.GoToOptionScreen -> {
                    preferenceStorage.setIsFirstInstall()
                }
            }
        }
    }

    data class UiState(
        val welcomeInfoList: List<OnboardingInfo> = emptyList(),
    )

    sealed interface UiAction {
        data object GoToOptionScreen : UiAction
    }
}