package io.github.faroffcode.rhvdo.core.domain

import io.github.faroffcode.rhvdo.core.common.Dispatcher
import io.github.faroffcode.rhvdo.core.common.NextDispatchers
import io.github.faroffcode.rhvdo.core.data.repository.MediaRepository
import io.github.faroffcode.rhvdo.core.data.repository.PreferencesRepository
import io.github.faroffcode.rhvdo.core.model.Folder
import io.github.faroffcode.rhvdo.core.model.Sort
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

class GetSortedFoldersUseCase @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val preferencesRepository: PreferencesRepository,
    @Dispatcher(NextDispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
) {

    operator fun invoke(folderPath: String? = null): Flow<List<Folder>> {
        return combine(
            mediaRepository.observeFolders(folderPath),
            preferencesRepository.applicationPreferences,
        ) { folders, preferences ->

            val nonExcludedDirectories = folders.filter {
                it.path !in preferences.excludeFolders
            }

            val sort = Sort(by = preferences.sortBy, order = preferences.sortOrder)
            nonExcludedDirectories.sortedWith(sort.folderComparator())
        }.flowOn(defaultDispatcher)
    }
}
