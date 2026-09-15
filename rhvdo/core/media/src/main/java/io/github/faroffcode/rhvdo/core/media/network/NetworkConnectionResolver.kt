package io.github.faroffcode.rhvdo.core.media.network

import io.github.faroffcode.rhvdo.core.model.NetworkConnection

/**
 * Looks up a saved connection by id.
 *
 * Declared here rather than taking a repository dependency because `core:data` already depends on
 * `core:media`; the binding lives in `core:data`, next to the repository that satisfies it.
 */
fun interface NetworkConnectionResolver {
    suspend fun connection(id: Long): NetworkConnection?
}
