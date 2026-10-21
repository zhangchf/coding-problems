package hackerrank

fun countIsolatedCommunicationGroups(links: Array<Array<Int>>, n: Int): Int {
    val map = mutableMapOf<Int, Set<Int>>()

    for (l in links) {
        if (l.size != 2 || l[0] >= n || l[1] >= n) return -1
        val set1 = map[l[0]] ?: setOf(l[0])
        val set2 = map[l[1]] ?: setOf(l[1])
        val newSet = (set1 + set2)
        map[l[0]] = newSet
        map[l[1]] = newSet
    }
    return map.values.distinct().size
}