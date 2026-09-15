package io.github.faroffcode.rhvdo

import io.github.faroffcode.rhvdo.core.data.repository.NetworkConnectionRepository
import io.github.faroffcode.rhvdo.core.media.network.keys.SshKeyStore
import io.github.faroffcode.rhvdo.core.model.NetworkAuthentication
import io.github.faroffcode.rhvdo.core.model.NetworkProtocol
import kotlinx.coroutines.flow.first

internal suspend fun initializeSshKeyStore(
    repository: NetworkConnectionRepository,
    sshKeyStore: SshKeyStore,
) {
    val referencedFileNames = try {
        repository.getConnections().first()
            .asSequence()
            .filter { connection ->
                connection.protocol == NetworkProtocol.SFTP &&
                    connection.authentication == NetworkAuthentication.SSH_KEY
            }
            .mapNotNull { connection ->
                connection.privateKeyFileName.trim()
                    .takeIf(SshKeyStore::isValidFileName)
            }
            .toSet()
    } catch (_: Throwable) {
        null
    }
    sshKeyStore.initialize(referencedFileNames)
}
