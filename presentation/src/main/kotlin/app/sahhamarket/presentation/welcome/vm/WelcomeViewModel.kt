package app.sahhamarket.presentation.welcome.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.datastore.PreferenceStorage
import app.sahhamarket.domain.model.WelcomeInfo
import app.sahhamarket.domain.usecase.welcome.GetWelcomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getWelcomeUseCase: GetWelcomeUseCase,
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
                is UiAction.GoToLocationScreen -> {
                    preferenceStorage.setIsFirstInstall()
                }
            }
        }
    }

    data class UiState(
        val welcomeInfoList: List<WelcomeInfo> = emptyList(),
    )

    sealed interface UiAction {
        data object GoToLocationScreen : UiAction
    }
}