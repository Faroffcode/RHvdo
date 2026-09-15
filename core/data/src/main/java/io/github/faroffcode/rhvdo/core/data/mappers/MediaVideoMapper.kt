package io.github.faroffcode.rhvdo.core.data.mappers

import io.github.faroffcode.rhvdo.core.common.Utils
import io.github.faroffcode.rhvdo.core.database.entities.MediumStateEntity
import io.github.faroffcode.rhvdo.core.media.services.MediaVideo
import io.github.faroffcode.rhvdo.core.model.Video
import java.util.Date

internal fun MediaVideo.toVideo(mediaState: MediumStateEntity? = null) = Video(
    id = id,
    uriString = uri.toString(),
    duration = duration,
    height = height,
    width = width,
    path = path,
    size = size,
    nameWithExtension = title,
    parentPath = parentPath,
    dateModified = dateModified,
    formattedDuration = Utils.formatDurationMillis(duration),
    formattedFileSize = Utils.formatFileSize(size),
    playbackPosition = mediaState?.playbackPosition,
    lastPlayedAt = mediaState?.lastPlayedTime?.let { Date(it) },
)
