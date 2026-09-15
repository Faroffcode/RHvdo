package io.github.faroffcode.rhvdo.core.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faroffcode.rhvdo.core.data.repository.LocalMediaRepository
import io.github.faroffcode.rhvdo.core.data.repository.LocalNetworkConnectionRepository
import io.github.faroffcode.rhvdo.core.data.repository.LocalPreferencesRepository
import io.github.faroffcode.rhvdo.core.data.repository.LocalPlaylistRepository
import io.github.faroffcode.rhvdo.core.data.repository.LocalSearchHistoryRepository
import io.github.faroffcode.rhvdo.core.data.repository.LocalVaultPinRepository
import io.github.faroffcode.rhvdo.core.data.repository.LocalVaultRepository
import io.github.faroffcode.rhvdo.core.data.repository.MediaRepository
import io.github.faroffcode.rhvdo.core.data.repository.NetworkConnectionRepository
import io.github.faroffcode.rhvdo.core.data.repository.PreferencesRepository
import io.github.faroffcode.rhvdo.core.data.repository.PlaylistRepository
import io.github.faroffcode.rhvdo.core.data.repository.SearchHistoryRepository
import io.github.faroffcode.rhvdo.core.data.repository.VaultPinRepository
import io.github.faroffcode.rhvdo.core.data.repository.VaultRepository
import io.github.faroffcode.rhvdo.core.media.network.NetworkConnectionResolver
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsPlaylistRepository(
        playlistRepository: LocalPlaylistRepository,
    ): PlaylistRepository

    @Binds
    fun bindsMediaRepository(
        videoRepository: LocalMediaRepository,
    ): MediaRepository

    @Binds
    @Singleton
    fun bindsPreferencesRepository(
        preferencesRepository: LocalPreferencesRepository,
    ): PreferencesRepository

    @Binds
    @Singleton
    fun bindsSearchHistoryRepository(
        searchHistoryRepository: LocalSearchHistoryRepository,
    ): SearchHistoryRepository

    @Binds
    @Singleton
    fun bindsVaultRepository(
        vaultRepository: LocalVaultRepository,
    ): VaultRepository

    @Binds
    @Singleton
    fun bindsVaultPinRepository(
        vaultPinRepository: LocalVaultPinRepository,
    ): VaultPinRepository

    @Binds
    @Singleton
    fun bindsNetworkConnectionRepository(
        networkConnectionRepository: LocalNetworkConnectionRepository,
    ): NetworkConnectionRepository

    companion object {
        /** Lets `core:media` resolve a playback uri's connection id without depending on `core:data`. */
        @Provides
        @Singleton
        fun providesNetworkConnectionResolver(
            repository: NetworkConnectionRepository,
        ): NetworkConnectionResolver = NetworkConnectionResolver { id -> repository.getConnection(id) }
    }
}
