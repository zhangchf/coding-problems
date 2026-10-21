package test

import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import kotlin.test.Test

class SupervisorScopeException {

    // A modified function that intentionally lets the exception propagate
    suspend fun fetchAndCrash(): String = supervisorScope {
        val deferred = async { throw IllegalStateException("Critical error") }
        deferred.await() // No try-catch here!
    }

    @Test
    fun fetchAndCrash_propagatesException() {
        // Assert that the suspending call throws the expected exception
        assertThrows(IllegalStateException::class.java) {
            runTest { // Nested runTest or simply calling the suspend function
                fetchAndCrash()
            }
        }
    }
}