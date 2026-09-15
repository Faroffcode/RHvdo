package io.github.faroffcode.rhvdo.core.media.network

import io.github.faroffcode.rhvdo.core.model.NetworkConnection

fun interface NetworkClientFactory {
    fun create(connection: NetworkConnection): NetworkClient
}
