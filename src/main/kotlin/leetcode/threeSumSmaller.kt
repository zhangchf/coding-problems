package leetcode


fun threeSumSmaller(nums: IntArray, target: Int): Int {
    quickSort(nums, 0, nums.size - 1)

    var result = 0
    for ((i, val1) in nums.withIndex()) {
        for (j in i+1 until nums.size) {
            val comp = target - nums[i] - nums[j]
            for (k in j+1 until nums.size) {
                if (nums[k] >= comp) break
                result++
            }
        }
    }
    return result
}

private fun quickSort(nums: IntArray, start: Int, end: Int) {
    if (start >= end) return

    var i = start
    var j = end
    val pivot = nums[start]

    while (i < j) {
        while (i < j && nums[j] > pivot) {
            j--
        }
        if (i < j) {
            nums[i] = nums[j]
            i++
        }
        while (i < j && nums[i] < pivot) {
            i++
        }
        if (i < j) {
            nums[j] = nums[i]
            j--
        }
    }
    nums[i] = pivot

    quickSort(nums, start, i - 1)
    quickSort(nums, i + 1, end)
}

fun main() {
    val nums = intArrayOf(1, 1, -2)
    quickSort(nums, 0, 2 )
    println(nums.contentToString())
}