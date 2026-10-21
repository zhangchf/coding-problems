package leetcode

fun getMedian(array: IntArray, size: Int, offset: Int = 0): Double {
    if (size <= 0) return 0.0
    if (size == 1) return array[0].toDouble()

    val mid = size / 2 - offset
    return if (size % 2 == 1) {
        array[mid].toDouble()
    } else {
        (array[mid - 1] + array[mid]) / 2.0
    }
}

fun getMedian(array1: IntArray, array2: IntArray): Double {
    val size = array1.size + array2.size
    val mid = size / 2

    fun getElement(pos: Int) = if (pos < array1.size) {
        array1[pos]
    } else {
        array2[pos - array1.size]
    }
    return if (size % 2 == 1) {
        getElement(mid).toDouble()
    } else {
        (getElement(mid - 1) + getElement(mid)) / 2.0
    }
}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    if (nums1.isEmpty()) {
        return getMedian(nums2, nums2.size)
    }
    if (nums2.isEmpty()) {
        return getMedian(nums1, nums1.size)
    }

    val (array1, array2) = if (nums1.first() < nums2.first()) {
        nums1 to nums2
    } else {
        nums2 to nums1
    }

    var insertPos = array1.binarySearch(array2.first()) + 1
    if (insertPos < 0) {
        insertPos = -insertPos
    }

    if (insertPos >= array1.size) {
        return getMedian(array1, array2)
    }

    val resultArray = mutableListOf<Int>()

    var startPos1 = insertPos
    var startPos2 = 0
    while (startPos1 < array1.size && startPos2 < array2.size) {
        if (array1[startPos1] <= array2[startPos2]) {
            resultArray.add(array1[startPos1++])
        } else {
            resultArray.add(array2[startPos2++])
        }
    }

    val leftArray: IntArray
    val leftArrayStartPos: Int
    val leftSize = if (startPos1 == array1.size) {
        leftArray = array2
        leftArrayStartPos = startPos2
        array2.size - startPos2
    } else {
        leftArray = array1
        leftArrayStartPos = startPos1
        array1.size - startPos1
    }

    val totalSize = insertPos + resultArray.size + leftSize

    //get median
    val size = totalSize
    val mid = size / 2

    fun getElement(pos: Int) = if (pos < insertPos) {
        array1[pos]
    } else if (pos < insertPos + resultArray.size) {
        resultArray[pos - insertPos]
    } else {
        leftArray[pos -insertPos - resultArray.size + leftArrayStartPos]
    }

    return if (size % 2 == 1) {
        getElement(mid).toDouble()
    } else {
        (getElement(mid - 1) + getElement(mid)) / 2.0
    }
}

fun main() {
//    println(findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)))
//    println(findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)))
//    println(findMedianSortedArrays(intArrayOf(0, 0), intArrayOf(0, 0)))
    println(findMedianSortedArrays(intArrayOf(2), intArrayOf(1, 3, 4)))
}