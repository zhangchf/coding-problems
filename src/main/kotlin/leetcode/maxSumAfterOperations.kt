package leetcode

import java.util.Arrays

fun maxSumAfterOperations(arr: IntArray, m: Int): Int {

    var i = 0
    var j = arr.size - 1

    while (i < j) {
        if (arr[i] <= arr[j]) {
            i++
            continue
        } else {
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp

        }
    }



    return 0
}