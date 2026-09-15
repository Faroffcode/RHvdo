package io.github.faroffcode.rhvdo.settings.navigation

import androidx.compose.runtime.SideEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.faroffcode.rhvdo.settings.screens.player.PlayerPreferencesScreen
import io.github.faroffcode.rhvdo.settings.screens.player.PlayerPreferencesViewModel
import kotlinx.serialization.Serializable

@Serializable
object PlayerPreferencesRoute : NavKey

fun NavBackStack<NavKey>.navigateToPlayerPreferences() {
    add(PlayerPreferencesRoute)
}

fun EntryProviderScope<NavKey>.playerPreferencesEntry(onNavigateUp: () -> Unit) {
    entry<PlayerPreferencesRoute> {
        val output = PlayerPreferencesViewModel.Output(
            navigateUp = onNavigateUp,
        )
        val viewModel = hiltViewModel<PlayerPreferencesViewModel, PlayerPreferencesViewModel.Factory>(
            creationCallback = { factory -> factory.create(output = output) },
        )
        SideEffect { viewModel.output = output }
        PlayerPreferencesScreen(viewModel = viewModel)
    }
}
