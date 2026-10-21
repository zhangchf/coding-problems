package leetcode

private fun quickSort(array: IntArray, low: Int, high: Int) {
    if (low < high) {
        val partitionIndex = partition(array, low, high)
        quickSort(array, low, partitionIndex - 1)
        quickSort(array, partitionIndex + 1, high)
    }
}

private fun partition(array: IntArray, low: Int, high: Int): Int {
    val pivot = array[low]

    var i = low
    var j = high
    while (i < j) {
        while (i < j && array[j] >= pivot) {
            j--
        }
        while (i < j && array[i] <= pivot) {
            i++
        }
        swap(array, i, j)
    }
    swap(array, low, i)
    return i
}

fun swap(array: IntArray, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}

fun main(args: Array<String>) {
    val array = intArrayOf(7, 9, 7, 4, 5, 8, 7, 8, 10, 7, 7, 9, 1)
    quickSort(array, 0, array.lastIndex)
    println(array.contentToString())

    val array1 = intArrayOf(7, 9, 8, 7, 8, 5, 4, 3, 9, 9)
    quickSort(array1, 0, array1.lastIndex)
    println(array1.contentToString())
}