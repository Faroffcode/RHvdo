package io.github.faroffcode.rhvdo.core.media

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faroffcode.rhvdo.core.media.network.DefaultNetworkClientFactory
import io.github.faroffcode.rhvdo.core.media.network.NetworkClientFactory
import io.github.faroffcode.rhvdo.core.media.network.keys.DefaultSshKeyStore
import io.github.faroffcode.rhvdo.core.media.network.keys.SshKeyStore
import io.github.faroffcode.rhvdo.core.media.services.LocalMediaOperationsService
import io.github.faroffcode.rhvdo.core.media.services.MediaOperationsService
import io.github.faroffcode.rhvdo.core.media.services.MediaService
import io.github.faroffcode.rhvdo.core.media.services.MediaStoreMediaService
import io.github.faroffcode.rhvdo.core.media.sync.LocalMediaSynchronizer
import io.github.faroffcode.rhvdo.core.media.sync.MediaSynchronizer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MediaModule {

    @Binds
    @Singleton
    fun bindNetworkClientFactory(factory: DefaultNetworkClientFactory): NetworkClientFactory

    @Binds
    @Singleton
    fun bindSshKeyStore(store: DefaultSshKeyStore): SshKeyStore

    @Binds
    @Singleton
    fun bindsMediaSynchronizer(
        mediaSynchronizer: LocalMediaSynchronizer,
    ): MediaSynchronizer

    @Binds
    @Singleton
    fun bindMediaOperationsService(
        mediaService: LocalMediaOperationsService,
    ): MediaOperationsService

    @Binds
    @Singleton
    fun bindMediaService(
        mediaService: MediaStoreMediaService,
    ): MediaService
}
