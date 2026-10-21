package test

import org.junit.Test
import java.util.PriorityQueue

class DataStructures {
    @Test
    fun testPriorityQueue() {
        val priorityQueue = PriorityQueue<Int>(100, compareBy { it })

        priorityQueue.offer(1)
        priorityQueue.peek()
        priorityQueue.poll()

        priorityQueue.add(2)

        priorityQueue.size
    }

    @Test
    fun testList() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEachIndexed { index, v -> v }

        for ((index, v) in list.withIndex()) {
            v
        }

        list.reversed()
    }

    @Test
    fun testComparable() {
        data class Person(val name: String, val age: Int)
        val persons = listOf(Person("Alice", 29), Person("Bob", 31))

        persons.sortedByDescending { it.age }

        data class Person1(val name: String, val age: Int): Comparable<Person1> {
            override fun compareTo(other: Person1): Int {
                return this.age.compareTo(other.age)
            }
        }
    }

    @Test
    fun testMap() {

    }
}