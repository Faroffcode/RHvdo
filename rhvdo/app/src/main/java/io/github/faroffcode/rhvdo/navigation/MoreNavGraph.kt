package io.github.faroffcode.rhvdo.navigation

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.faroffcode.rhvdo.feature.more.navigation.historyEntry
import io.github.faroffcode.rhvdo.feature.more.navigation.moreEntry
import io.github.faroffcode.rhvdo.feature.more.navigation.navigateToHistory
import io.github.faroffcode.rhvdo.feature.more.navigation.navigateToTrash
import io.github.faroffcode.rhvdo.feature.more.navigation.trashEntry
import io.github.faroffcode.rhvdo.feature.videopicker.navigation.navigateToVault
import io.github.faroffcode.rhvdo.feature.videopicker.navigation.vaultEntry
import io.github.faroffcode.rhvdo.settings.navigation.navigateToSettings

fun EntryProviderScope<NavKey>.moreNavGraph(
    context: Context,
    backStack: NavBackStack<NavKey>,
) {
    moreEntry(
        onHistoryClick = backStack::navigateToHistory,
        onPlayVideo = { context.startPlayback(it.toUri()) },
        onSettingsClick = backStack::navigateToSettings,
        onTrashClick = backStack::navigateToTrash,
        onVaultClick = backStack::navigateToVault,
    )

    historyEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        onPlayVideo = { context.startPlayback(it.toUri()) },
    )

    trashEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        onPlayVideo = { context.startPlayback(it.toUri()) },
    )

    vaultEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        // Vault files are served through FileProvider, so read access must be granted at
        // playback time for both PlayerActivity and the (separate) PlayerService component.
        onPlayVideo = { uri -> context.startPlayback(uri, grantReadPermission = true) },
        onPlayVideos = { uris -> context.startPlayback(uris, grantReadPermission = true) },
    )
}
