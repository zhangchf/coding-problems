package coroutine

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineExceptionHandler got $throwable")
    }
    val deferred = GlobalScope.async(handler) { // root coroutine with async
        delay(1000)
        println("Throwing exception from async")
        throw ArithmeticException() // Nothing is printed, relying on user to call await
    }

//    yield()
    val job = GlobalScope.launch(handler) { // root coroutine with launch
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
    }


    try {
        deferred.await()
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught $e")
    }

    job.join()
    println("Joined failed job")
}
