package leetcode

import java.util.LinkedList

fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val indegree = mutableMapOf<Int, Int>()
    val adj = mutableMapOf<Int, Array<Int>>()

    for (p in prerequisites) {
        val p0 = p[0]
        val p1 = p[1]
        indegree[p0] = (indegree[p0] ?: 0) + 1
        indegree[p1] = indegree[p1] ?: 0
        adj[p1] = (adj[p1] ?: arrayOf()) + p0
    }

    var nodesToVisit = indegree.size

    val queue = LinkedList<Int>()
    for (p in indegree) {
        if (p.value == 0) {
            queue.add(p.key)
        }
    }

    while (queue.isNotEmpty()) {
        val p = queue.removeFirst()
        nodesToVisit--
        for (q in adj[p] ?: emptyArray()) {
            indegree[q] = (indegree[q] ?: 0) - 1
            if (indegree[q] == 0) {
                queue.add(q)
            }
        }
    }

    if (nodesToVisit == 0) return true

    return false
}

fun main() {
    println(canFinish(2, arrayOf(intArrayOf(1, 0))))
}