package io.github.faroffcode.rhvdo.feature.videopicker.screens

import io.github.faroffcode.rhvdo.core.model.Folder

sealed interface MediaState {
    data object Loading : MediaState
    data class Success(val data: Folder?) : MediaState
}
