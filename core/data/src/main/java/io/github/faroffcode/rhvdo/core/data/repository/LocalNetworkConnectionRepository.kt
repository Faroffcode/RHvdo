package io.github.faroffcode.rhvdo.core.data.repository

import io.github.faroffcode.rhvdo.core.database.dao.NetworkConnectionDao
import io.github.faroffcode.rhvdo.core.database.entities.NetworkConnectionEntity
import io.github.faroffcode.rhvdo.core.model.NetworkAuthentication
import io.github.faroffcode.rhvdo.core.model.NetworkConnection
import io.github.faroffcode.rhvdo.core.model.NetworkProtocol
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class LocalNetworkConnectionRepository @Inject constructor(
    private val networkConnectionDao: NetworkConnectionDao,
) : NetworkConnectionRepository {

    override fun getConnections(): Flow<List<NetworkConnection>> =
        networkConnectionDao.getAll().map { entities -> entities.map { it.toModel() } }

    override suspend fun getConnection(id: Long): NetworkConnection? =
        networkConnectionDao.getById(id)?.toModel()

    override suspend fun upsert(connection: NetworkConnection): Long =
        networkConnectionDao.upsert(connection.toEntity())

    override suspend fun delete(id: Long) = networkConnectionDao.deleteById(id)

    private fun NetworkConnectionEntity.toModel() = NetworkConnection(
        id = id,
        name = name,
        protocol = runCatching { NetworkProtocol.valueOf(protocol) }.getOrDefault(NetworkProtocol.SMB),
        host = host,
        port = port,
        path = path,
        username = username,
        password = password,
        useHttps = useHttps,
        authentication = runCatching { NetworkAuthentication.valueOf(authentication) }
            .getOrDefault(NetworkAuthentication.PASSWORD),
        privateKeyFileName = privateKeyFileName,
        privateKeyPassphrase = privateKeyPassphrase,
        hostKeyFingerprint = hostKeyFingerprint,
    )

    private fun NetworkConnection.toEntity() = NetworkConnectionEntity(
        id = id,
        name = name,
        protocol = protocol.name,
        host = host,
        port = port,
        path = path,
        username = username,
        password = password,
        useHttps = useHttps,
        authentication = authentication.name,
        privateKeyFileName = privateKeyFileName,
        privateKeyPassphrase = privateKeyPassphrase,
        hostKeyFingerprint = hostKeyFingerprint,
    )
}
