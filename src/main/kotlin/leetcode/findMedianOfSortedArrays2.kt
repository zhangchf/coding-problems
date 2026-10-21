package leetcode

fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
    if (nums1.isEmpty() && nums2.isEmpty()) return 0.0

    fun getMedian(arr: IntArray): Double {
        val isOdd = arr.size % 2 == 1
        val mid = arr.size / 2

        return if (isOdd) {
            arr[mid].toDouble()
        } else {
            (arr[mid] + arr[mid -1])/2.0
        }
    }

    if (nums1.isEmpty()) return getMedian(nums2)
    if (nums2.isEmpty()) return getMedian(nums1)

    val mid = (nums1.size + nums2.size) / 2 + 1
    val isOdd = (nums1.size + nums2.size) % 2 == 1

    var i = -1
    var j = -1
    while (i < nums1.size -1 && j < nums2.size - 1) {
        if (i+1 + j+1 >= mid) break
        if (nums1[i+1] <= nums2[j+1]) i++ else j++
    }

    while (i+1 + j+1 < mid) {
        if (i+1 == nums1.size) j++ else i++
    }

    fun IntArray.element(index:Int) = if (index >= 0) this[index] else Int.MIN_VALUE

    if (isOdd) {
        return maxOf(nums1.element(i), nums2.element(j)).toDouble()
    } else {
        val (i1, i2) = nums1.element(i) to nums1.element(i-1)
        val (j1, j2) = nums2.element(j) to nums2.element(j-1)

        val (last1, last2) = if (i2 >= j1) {
            i1 to i2
        } else {
            if (i1 >= j2) {
                if (i1 >= j1) {
                    i1 to j1
                } else {
                    j1 to i1
                }
            } else {
                j1 to j2
            }
        }
        return (last1 + last2) / 2.0
    }
}

fun main() {
    check(findMedianSortedArrays2(intArrayOf(1), intArrayOf(1)) == 1.0)
    check(findMedianSortedArrays2(intArrayOf(), intArrayOf(2,3)) == 2.5)
    check(findMedianSortedArrays2(intArrayOf(1, 3), intArrayOf(2)) == 2.0)
    check(findMedianSortedArrays2(intArrayOf(1, 2), intArrayOf(3, 4)) == 2.5)
    check(findMedianSortedArrays2(intArrayOf(0, 0), intArrayOf(0, 0)) == 0.0)
    check(findMedianSortedArrays2(intArrayOf(2), intArrayOf(1, 3, 4)) == 2.5)
    check(findMedianSortedArrays2(intArrayOf(), intArrayOf(1)) == 1.0)
    check(findMedianSortedArrays2(intArrayOf(2,2,4,4), intArrayOf(2,2,2,4,4)) == 2.0)
    check(findMedianSortedArrays2(intArrayOf(1, 3), intArrayOf(2,7)) == 2.5)
    check(findMedianSortedArrays2(intArrayOf(1,2,3,4,5), intArrayOf(6,7,8,9,10,11,12,13,14,15,16,17)) == 9.0)
    check(findMedianSortedArrays2(intArrayOf(-10, -9, -8), intArrayOf(1,2)) == -8.0)
}