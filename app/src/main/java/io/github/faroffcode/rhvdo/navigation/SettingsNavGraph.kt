package io.github.faroffcode.rhvdo.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.faroffcode.rhvdo.settings.Setting
import io.github.faroffcode.rhvdo.settings.navigation.aboutPreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.appearancePreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.audioPreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.folderPreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.generalPreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.gesturePreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.librariesEntry
import io.github.faroffcode.rhvdo.settings.navigation.mediaLibraryPreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.navigateToAboutPreferences
import io.github.faroffcode.rhvdo.settings.navigation.navigateToAppearancePreferences
import io.github.faroffcode.rhvdo.settings.navigation.navigateToAudioPreferences
import io.github.faroffcode.rhvdo.settings.navigation.navigateToFolderPreferencesScreen
import io.github.faroffcode.rhvdo.settings.navigation.navigateToGeneralPreferences
import io.github.faroffcode.rhvdo.settings.navigation.navigateToGesturePreferences
import io.github.faroffcode.rhvdo.settings.navigation.navigateToLibraries
import io.github.faroffcode.rhvdo.settings.navigation.navigateToMediaLibraryPreferencesScreen
import io.github.faroffcode.rhvdo.settings.navigation.navigateToPlayerPreferences
import io.github.faroffcode.rhvdo.settings.navigation.navigateToSubtitlePreferences
import io.github.faroffcode.rhvdo.settings.navigation.navigateToThumbnailPreferencesScreen
import io.github.faroffcode.rhvdo.settings.navigation.playerPreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.settingsEntry
import io.github.faroffcode.rhvdo.settings.navigation.subtitlePreferencesEntry
import io.github.faroffcode.rhvdo.settings.navigation.thumbnailPreferencesEntry

fun EntryProviderScope<NavKey>.settingsNavGraph(
    backStack: NavBackStack<NavKey>,
) {
    settingsEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        onItemClick = { setting ->
            when (setting) {
                Setting.APPEARANCE -> backStack.navigateToAppearancePreferences()
                Setting.MEDIA_LIBRARY -> backStack.navigateToMediaLibraryPreferencesScreen()
                Setting.PLAYER -> backStack.navigateToPlayerPreferences()
                Setting.GESTURES -> backStack.navigateToGesturePreferences()
                Setting.AUDIO -> backStack.navigateToAudioPreferences()
                Setting.SUBTITLE -> backStack.navigateToSubtitlePreferences()
                Setting.GENERAL -> backStack.navigateToGeneralPreferences()
                Setting.ABOUT -> backStack.navigateToAboutPreferences()
            }
        },
    )
    appearancePreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    mediaLibraryPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
        onFolderSettingClick = backStack::navigateToFolderPreferencesScreen,
        onThumbnailSettingClick = backStack::navigateToThumbnailPreferencesScreen,
    )
    thumbnailPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    folderPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    playerPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    gesturePreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    audioPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    subtitlePreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    generalPreferencesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    aboutPreferencesEntry(
        onLibrariesClick = backStack::navigateToLibraries,
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
    librariesEntry(
        onNavigateUp = { backStack.removeLastIfNotRoot() },
    )
}
