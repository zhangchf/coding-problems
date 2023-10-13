package test

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class Test {

    @Test
    fun testFloatingPointNumber() {
        println(0.1 + 0.2)
        Assert.assertNotEquals(0.3, 0.1 + 0.2)
        assertEquals(3, 1 + 2)
    }
}