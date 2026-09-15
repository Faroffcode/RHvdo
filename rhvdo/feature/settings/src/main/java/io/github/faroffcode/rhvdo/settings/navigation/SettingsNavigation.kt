package io.github.faroffcode.rhvdo.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.faroffcode.rhvdo.settings.Setting
import io.github.faroffcode.rhvdo.settings.SettingsOutput
import io.github.faroffcode.rhvdo.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute : NavKey

fun NavBackStack<NavKey>.navigateToSettings() {
    add(SettingsRoute)
}

fun EntryProviderScope<NavKey>.settingsEntry(onNavigateUp: () -> Unit, onItemClick: (Setting) -> Unit) {
    entry<SettingsRoute> {
        SettingsScreen(output = SettingsOutput(navigateUp = onNavigateUp, openSetting = onItemClick))
    }
}
