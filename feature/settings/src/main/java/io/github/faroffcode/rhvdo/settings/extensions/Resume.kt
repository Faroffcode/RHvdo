package io.github.faroffcode.rhvdo.settings.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.faroffcode.rhvdo.core.model.Resume
import io.github.faroffcode.rhvdo.core.ui.R

@Composable
fun Resume.name(): String {
    val stringRes = when (this) {
        Resume.YES -> R.string.yes
        Resume.NO -> R.string.no
    }

    return stringResource(id = stringRes)
}
