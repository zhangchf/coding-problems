package coroutine

import kotlinx.coroutines.*
import java.io.IOException

fun main() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("The child is sleeping")
                    delay(Long.MAX_VALUE)
                } catch (e: Exception) {
                    println("Caught exception: $e")
                } finally {
                    println("The child is cancelled")
                }
            }
            // Give our child a chance to execute and print using yield
            yield()
            println("Throwing an exception from the scope")
            throw AssertionError()
        }
    } catch(e: AssertionError) {
        println("Caught an assertion error")
    }

    var a : String? = "b"
    val b = a ?: throw IllegalStateException("")
    println(b)
}