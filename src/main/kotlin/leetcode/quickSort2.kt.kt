package leetcode


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