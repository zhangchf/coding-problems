package leetcode

fun twoSum(nums: IntArray, target: Int): IntArray {
    val sorted = if (nums.isSorted()) nums else nums.sortedArray()
    for((i, eleA) in sorted.withIndex()) {
        val eleB = target - eleA
        var indexB = sorted.binarySearch(eleB)
        if (indexB < 0) continue
        if (indexB == i) {
            indexB = sorted.binarySearch(eleB, indexB + 1)
            if (indexB < 0) continue
        }
        return if (sorted === nums) {
            intArrayOf(i, indexB)
        } else {
            intArrayOf(nums.indexOf(eleA), nums.lastIndexOf(eleB))
        }
    }
    return intArrayOf()
}

fun main(args: Array<String>) {
//    println(twoSum(intArrayOf(3, 2, 4), 6).contentToString())
//    println(twoSum(intArrayOf(3, 3), 6).contentToString())
    println(twoSum(intArrayOf(-1,-2,-3,-4,-5), -8).contentToString())
}