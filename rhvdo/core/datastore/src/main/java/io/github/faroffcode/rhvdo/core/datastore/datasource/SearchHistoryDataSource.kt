package io.github.faroffcode.rhvdo.core.datastore.datasource

import androidx.datastore.core.DataStore
import io.github.faroffcode.rhvdo.core.common.Logger
import io.github.faroffcode.rhvdo.core.model.SearchHistory
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchHistoryDataSource @Inject constructor(
    private val searchHistoryDataStore: DataStore<SearchHistory>,
) {

    companion object {
        private const val TAG = "SearchHistoryDataSource"
    }

    val searchHistory: Flow<SearchHistory> = searchHistoryDataStore.data

    suspend fun update(
        transform: suspend (SearchHistory) -> SearchHistory,
    ) {
        try {
            searchHistoryDataStore.updateData(transform)
        } catch (ioException: Exception) {
            Logger.logError(TAG, "Failed to update search history: $ioException")
        }
    }
}
