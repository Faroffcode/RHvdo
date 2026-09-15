package io.github.faroffcode.rhvdo.settings.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.faroffcode.rhvdo.core.model.ThemeConfig
import io.github.faroffcode.rhvdo.core.ui.R

@Composable
fun ThemeConfig.name(): String {
    val stringRes = when (this) {
        ThemeConfig.SYSTEM -> R.string.system_default
        ThemeConfig.OFF -> R.string.off
        ThemeConfig.ON -> R.string.on
    }

    return stringResource(id = stringRes)
}
