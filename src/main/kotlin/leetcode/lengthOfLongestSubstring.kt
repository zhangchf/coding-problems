package leetcode

fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) return 0

    var i = 0
    var j = 1
    var maxLength = 1
    while (j < s.length) {
        val firstJ = s.substring(i, j).indexOf(s[j])
        if (firstJ == -1) {
            val substrLen = j - i + 1
            if (maxLength < substrLen) {
                maxLength = substrLen
            }
            j++
        } else {
            i += firstJ + 1
            j++
        }
    }

    return maxLength
}

fun main() {
//    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring("bbbbb"))
}