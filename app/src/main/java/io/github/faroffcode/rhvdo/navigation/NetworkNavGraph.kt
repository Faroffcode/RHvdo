package io.github.faroffcode.rhvdo.navigation

import android.content.Context
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.faroffcode.rhvdo.feature.network.navigation.addConnectionEntry
import io.github.faroffcode.rhvdo.feature.network.navigation.navigateToAddConnection
import io.github.faroffcode.rhvdo.feature.network.navigation.navigateToNetworkBrowse
import io.github.faroffcode.rhvdo.feature.network.navigation.networkBrowseEntry
import io.github.faroffcode.rhvdo.feature.network.navigation.networkEntry
import io.github.faroffcode.rhvdo.settings.navigation.navigateToSettings

fun EntryProviderScope<NavKey>.networkNavGraph(
    context: Context,
    backStack: NavBackStack<NavKey>,
) {
    networkEntry(
        onAddConnection = { backStack.navigateToAddConnection() },
        onEditConnection = { id -> backStack.navigateToAddConnection(id) },
        onOpenConnection = { id -> backStack.navigateToNetworkBrowse(id) },
        onSettingsClick = backStack::navigateToSettings,
        onOpenStream = { uri -> context.startPlayback(uri) },
    )

    addConnectionEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )

    networkBrowseEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        onPlayVideo = { uri -> context.startPlayback(uri) },
        onNavigateToFolder = { id, path -> backStack.navigateToNetworkBrowse(id, path) },
    )
}
