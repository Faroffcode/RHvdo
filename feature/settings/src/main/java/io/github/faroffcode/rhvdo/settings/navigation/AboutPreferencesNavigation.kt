package io.github.faroffcode.rhvdo.settings.navigation

import androidx.compose.runtime.SideEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.faroffcode.rhvdo.settings.screens.about.AboutPreferencesScreen
import io.github.faroffcode.rhvdo.settings.screens.about.AboutPreferencesViewModel
import io.github.faroffcode.rhvdo.settings.screens.about.LibrariesScreen
import io.github.faroffcode.rhvdo.settings.screens.about.LibrariesViewModel
import kotlinx.serialization.Serializable

@Serializable
object AboutPreferencesRoute : NavKey

@Serializable
object LibrariesRoute : NavKey

fun NavBackStack<NavKey>.navigateToAboutPreferences() {
    add(AboutPreferencesRoute)
}

fun NavBackStack<NavKey>.navigateToLibraries() {
    add(LibrariesRoute)
}

fun EntryProviderScope<NavKey>.aboutPreferencesEntry(
    onLibrariesClick: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    entry<AboutPreferencesRoute> {
        val output = AboutPreferencesViewModel.Output(navigateUp = onNavigateUp, openLibraries = onLibrariesClick)
        val viewModel = hiltViewModel<AboutPreferencesViewModel, AboutPreferencesViewModel.Factory>(
            creationCallback = { factory -> factory.create(output = output) },
        )
        SideEffect { viewModel.output = output }
        AboutPreferencesScreen(viewModel = viewModel)
    }
}

fun EntryProviderScope<NavKey>.librariesEntry(onNavigateUp: () -> Unit) {
    entry<LibrariesRoute> {
        val output = LibrariesViewModel.Output(navigateUp = onNavigateUp)
        val viewModel = hiltViewModel<LibrariesViewModel, LibrariesViewModel.Factory>(
            creationCallback = { factory -> factory.create(output = output) },
        )
        SideEffect { viewModel.output = output }
        LibrariesScreen(viewModel = viewModel)
    }
}
