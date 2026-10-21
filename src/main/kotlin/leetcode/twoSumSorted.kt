package leetcode

// 2-pointers method
fun twoSumSorted(numbers: IntArray, target: Int): IntArray {
     var i = 0
     var j = numbers.size - 1
     while (i < j) {
         val sum = numbers[i] + numbers[j]
         if (sum == target) {
             return intArrayOf(i + 1, j + 1)
         } else if (sum > target) {
             j--
         } else {
             i++
         }
     }
    return intArrayOf()
}

fun main() {
    println(twoSumSorted(intArrayOf(5, 25, 75), 100).contentToString())
}