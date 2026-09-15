package io.github.faroffcode.rhvdo.core.data.mappers

import io.github.faroffcode.rhvdo.core.media.services.MediaFolder
import io.github.faroffcode.rhvdo.core.model.Folder

internal fun MediaFolder.toFolder() = Folder(
    name = name,
    path = path,
    dateModified = dateModified,
    totalSize = totalSize,
    totalDuration = totalDuration,
    videosCount = videosCount,
    foldersCount = foldersCount,
)
