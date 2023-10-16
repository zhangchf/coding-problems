package test

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class FloatingPointNumberTest {

    @Test
    fun testFloatingPointNumber() {
        assertEquals(3, 1 + 2)

        println(0.1 + 0.2)
        assertNotEquals(0.3, 0.1 + 0.2)

        val x = 0.1;
        val y = 0.2;
        val z = 0.3
        println((x + y) + z)
        println(x + (y + z))

        assertNotEquals((x + y) + z, 0.6)
        assertEquals(x + (y + z), 0.6)
    }
}