package io.github.faroffcode.rhvdo.settings.navigation

import androidx.compose.runtime.SideEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.faroffcode.rhvdo.settings.screens.appearance.AppearancePreferencesScreen
import io.github.faroffcode.rhvdo.settings.screens.appearance.AppearancePreferencesViewModel
import kotlinx.serialization.Serializable

@Serializable
object AppearancePreferencesRoute : NavKey

fun NavBackStack<NavKey>.navigateToAppearancePreferences() {
    add(AppearancePreferencesRoute)
}

fun EntryProviderScope<NavKey>.appearancePreferencesEntry(onNavigateUp: () -> Unit) {
    entry<AppearancePreferencesRoute> {
        val output = AppearancePreferencesViewModel.Output(
            navigateUp = onNavigateUp,
        )
        val viewModel = hiltViewModel<AppearancePreferencesViewModel, AppearancePreferencesViewModel.Factory>(
            creationCallback = { factory -> factory.create(output = output) },
        )
        SideEffect { viewModel.output = output }
        AppearancePreferencesScreen(viewModel = viewModel)
    }
}
