package leetcode

// Sliding-Window approach, each round with a different unique character number.
fun longestSubstring(s: String, k: Int): Int {
    val uniqueMap = mutableMapOf<Char, Int>()
    for (c in s) {
        uniqueMap[c] = (uniqueMap[c] ?: 0) + 1
    }
    val maxUnique = uniqueMap.size

    var maxLength = 0
    for (curUnique in 1..maxUnique) {
        var i = 0
        var j = 0
        val countMap = mutableMapOf<Char, Int>()
        var countAtLeastK = 0
        while (j < s.length) {
            if (countMap.size <= curUnique) {
                countMap[s[j]] = (countMap[s[j]] ?: 0) + 1
                if (countMap[s[j]] == k) {
                    countAtLeastK++
                }
                if (countMap.size == curUnique && countAtLeastK == countMap.size) {
                    maxLength = maxOf(maxLength, j - i + 1)
                }
//                if (countMap.size == curUnique) {
//                    if (countMap.all { it.value >= k }) {
//                        maxLength = maxOf(maxLength, j - i + 1)
//                    }
//                }
                j++
            } else {
                if (countMap[s[i]]!! == k) {
                    countAtLeastK--
                }
                countMap[s[i]] = countMap[s[i]]!! - 1
                if (countMap[s[i]] == 0) {
                    countMap.remove(s[i])
                }
                i++
            }
        }
    }
    return maxLength
}

// Divide and Conquer
fun longestSubstring2(s: String, k: Int): Int {
    fun backtrack(s: String, start: Int, end: Int, k: Int): Int {
        if (start > end || end - start + 1 < k) return 0

        val uniqueMap = mutableMapOf<Char, Int>()
        for (i in start..end) {
            uniqueMap[s[i]] = (uniqueMap[s[i]] ?: 0) + 1
        }

        for (i in start..end) {
            if (uniqueMap[s[i]]!! >= k) continue
            val mid = i
            var mid2 = i + 1
            while (mid2 < end && uniqueMap[s[mid2]]!! < k) {
                mid2++
            }
            return maxOf(
                backtrack(s, start, mid - 1, k),
                backtrack(s, mid2, end, k)
            )
        }
        return end - start + 1
    }

    return backtrack(s, 0, s.length - 1, k)
}

fun main(args: Array<String>) {
//    println(longestSubstring(s = "ababbc", k = 2))
    println(longestSubstring2("aaabb", 3))
}