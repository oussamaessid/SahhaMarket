package app.sahhamarket.presentation.profil.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sahhamarket.domain.model.User
import app.sahhamarket.domain.usecase.profile.GetProfileInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileInfoUseCase: GetProfileInfoUseCase,
) : ViewModel() {

    private val _stateProfile = MutableStateFlow(ProfileUiState())
    val stateProfile: StateFlow<ProfileUiState> = _stateProfile.asStateFlow()

    init {
        processAction(UiAction.LoadData)
    }

    fun processAction(action: UiAction) {
        viewModelScope.launch {
            when (action) {
                UiAction.LoadData -> loadProfileData()
                is UiAction.Logout -> logout()
                is UiAction.ChangeLanguage -> changeLanguage(action.language)
            }
        }
    }

    private suspend fun loadProfileData() {
        _stateProfile.update { it.copy(isLoading = true) }

        val userResult = getProfileInfoUseCase()

        _stateProfile.update {
            it.copy(
                isLoading = false,
                user = userResult.getOrNull(),
                hasError = userResult.isFailure
            )
        }
    }

    private fun logout() {
        // TODO: Call logout use case, clear tokens.
    }

    private fun changeLanguage(language: String) {
        _stateProfile.update { it.copy(language = language) }
        // Optionally: persist language setting in a data store or shared prefs
    }

    sealed interface UiAction {
        object LoadData : UiAction
        object Logout : UiAction
        data class ChangeLanguage(val language: String) : UiAction
    }

    data class ProfileUiState(
        val isLoading: Boolean = false,
        val user: User? = null,
        val stats: app.sahhamarket.domain.model.UserStats? = null,
        val language: String = "en",
        val hasError: Boolean = false
    )
}
