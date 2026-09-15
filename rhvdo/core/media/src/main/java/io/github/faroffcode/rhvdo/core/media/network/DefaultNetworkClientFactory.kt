package io.github.faroffcode.rhvdo.core.media.network

import io.github.faroffcode.rhvdo.core.media.network.clients.FtpClient
import io.github.faroffcode.rhvdo.core.media.network.clients.SftpClient
import io.github.faroffcode.rhvdo.core.media.network.clients.SmbClient
import io.github.faroffcode.rhvdo.core.media.network.clients.WebDavClient
import io.github.faroffcode.rhvdo.core.media.network.keys.SshKeyStore
import io.github.faroffcode.rhvdo.core.model.NetworkConnection
import io.github.faroffcode.rhvdo.core.model.NetworkProtocol
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNetworkClientFactory @Inject constructor(
    private val sshKeyStore: SshKeyStore,
) : NetworkClientFactory {
    override fun create(connection: NetworkConnection): NetworkClient = when (connection.protocol) {
        NetworkProtocol.SMB -> SmbClient(connection)
        NetworkProtocol.FTP -> FtpClient(connection)
        NetworkProtocol.SFTP -> SftpClient(connection, sshKeyStore)
        NetworkProtocol.WEBDAV -> WebDavClient(connection)
    }
}
