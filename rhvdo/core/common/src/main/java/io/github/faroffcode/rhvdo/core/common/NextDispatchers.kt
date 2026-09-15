package io.github.faroffcode.rhvdo.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val niaDispatcher: NextDispatchers)

enum class NextDispatchers {
    Default,
    IO,
}
