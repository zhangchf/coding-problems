package test

import org.junit.Test

class MapTest {

    @Test
    fun testMap() {
        val map = mutableMapOf<String, Int>()
        val key = "key"
        map[key] = 1
        if (key in map) {
            map.remove(key)
        }
    }
}