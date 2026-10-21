package leetcode

fun totalNumbers(digits: IntArray): Int {
    val numSet = mutableSetOf<Int>()

    for ((i, element) in digits.withIndex()) {
        val digitI = element
        if (digitI % 2 != 0) continue
        for ((j, element1) in digits.withIndex()) {
            if (j == i) continue
            if (element1 == 0) continue
            for ((k, element2) in digits.withIndex()) {
                if (k == i || k == j) continue
                val num = element1 * 100 + element2 * 10 + digitI
                numSet.add(num)
            }
        }
    }
    return numSet.size
}