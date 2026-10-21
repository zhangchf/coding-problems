package coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    flowOf(1, 2, 3).map { it * 2 }.collect { println(it) }

    flow {
        emit(1)
        emit(2)
    }
        .flowOn(Dispatchers.Default)
        .onStart { emit(3) }
        .catch {  }
        .cancellable()
        .onCompletion {  }
        .collect { println(it) }

}