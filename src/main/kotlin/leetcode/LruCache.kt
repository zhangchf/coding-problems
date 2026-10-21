package leetcode

class LRUCache(val capacity: Int) {
    private class Node(
        var key: Int,
        var value: Int,
        var prev: Node? = null,
        var next: Node? = null
    )

    private val nodeMap = mutableMapOf<Int, Node>()
    private val nodeList = Node(Int.MAX_VALUE, Int.MAX_VALUE).apply {
        this.prev = this
        this.next = this
    }

    private var count = 0
    private val maximum = capacity

    private fun appendNode(node: Node): Node {
        val prevLast = nodeList.prev

        node.prev = prevLast
        node.next = prevLast!!.next
        prevLast.next = node
        nodeList.prev = node
        return node
    }

    private fun removeNode(node: Node) {
        node.prev!!.next = node.next
        node.next!!.prev = node.prev
    }

    private fun refreshNode(node: Node) {
        removeNode(node)
        appendNode(node)
    }

    fun get(key: Int): Int {
        val node = nodeMap[key] ?: return -1
        refreshNode(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        if (key in nodeMap) {
            val node = nodeMap[key]
            node!!.value = value
            refreshNode(node)
        } else {
            if (count == maximum) {
                val oldestNode = nodeList.next
                nodeMap.remove(oldestNode!!.key)
                removeNode(oldestNode)
                count--
            }
            nodeMap[key] = appendNode(Node(key, value, null, null))
            count++
        }
    }
}

fun test1() {
    val cache = LRUCache(2)
    cache.put(1, 1)
    cache.put(2, 2)
    println(cache.get(1))
    cache.put(3, 3)
    println(cache.get(2))
    cache.put(4, 4)
    println(cache.get(1))
    println(cache.get(3))
    println(cache.get(4))
}

fun test2() {
    val cache = LRUCache(1)
    cache.put(2, 1)
    println(cache.get(2))
    cache.put(3, 2)
    println(cache.get(2))
    println(cache.get(3))
}

fun test3() {
    val cache = LRUCache(2)
    cache.put(2, 1)
    cache.put(3, 2)
    println(cache.get(3))
    println(cache.get(2))
    cache.put(4, 3)
    println(cache.get(2))
    println(cache.get(3))
    println(cache.get(4))
}


fun main() {
    test3()
}