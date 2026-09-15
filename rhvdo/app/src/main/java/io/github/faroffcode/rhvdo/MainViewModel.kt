package io.github.faroffcode.rhvdo

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faroffcode.rhvdo.core.data.repository.PreferencesRepository
import io.github.faroffcode.rhvdo.core.model.ApplicationPreferences
import io.github.faroffcode.rhvdo.core.ui.base.MviViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) : MviViewModel<MainActivityUiState, Nothing>() {

    private val stateInternal = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Loading)
    override val state: StateFlow<MainActivityUiState> = stateInternal.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.applicationPreferences.collect { preferences ->
                stateInternal.update { MainActivityUiState.Success(preferences) }
            }
        }
    }

    // Application preferences are observed only; this view model has no input actions.
    override fun onAction(action: Nothing) = Unit
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val preferences: ApplicationPreferences) : MainActivityUiState
}
