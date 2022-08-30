/*
 * Complete the 'miniMaxSum' function below.
 *
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

fun miniMaxSum(arr: Array<Int>): Unit {
    // Write your code here

    val total : Long = arr.fold(0L) { acc: Long, i: Int ->
        acc + i
    }

    var min : Long = total
    var max : Long = 0L

    for (i in arr) {
        val sum = total - i
        min = min.coerceAtMost(sum)
        max = max.coerceAtLeast(sum)
    }

    println("$min $max")

}

fun main() {

    val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    miniMaxSum(arr)
}
