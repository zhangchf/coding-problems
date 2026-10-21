package leetcode

// This always keep the current window valid.
fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
    if (k <= 0) return 0
    var left = 0
    var right = 0
    var maxLength = 0
    val counter = mutableMapOf<Char, Int>()
    while (right < s.length) {
        counter[s[right]] = (counter[s[right]] ?: 0) + 1

        while (counter.size > k) {
            counter[s[left]] = counter[s[left]]!! - 1
            if (counter[s[left]] == 0) {
                counter.remove(s[left])
            }
            left++
        }

        maxLength = maxOf(maxLength, right - left + 1)
        right++
    }
    return maxLength
}

// This always keep the current max length window,
// but the window might not be valid.
fun lengthOfLongestSubstringKDistinct2(s: String, k: Int): Int {
    if (k <= 0) return 0

    var left = 0
    var right = 0
    var maxLength = 0
    val counter = mutableMapOf<Char, Int>()
    while (right < s.length) {
        counter[s[right]] = (counter[s[right]] ?: 0) + 1

        if (counter.size <= k) {
            maxLength++
        } else {
            counter[s[left]] = counter[s[left]]!! - 1
            if (counter[s[left]] == 0) {
                counter.remove(s[left])
            }
            left++
        }
        right++

    }
    return maxLength
}

fun main() {
    println(lengthOfLongestSubstringKDistinct("abaccc", 2))
}