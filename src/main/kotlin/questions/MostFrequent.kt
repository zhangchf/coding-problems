package questions

/*
 * Complete the 'mostFrequent' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

fun mostFrequent(arr: Array<Int>): Int {
    "".toInt()
    return arr.groupBy { it }.maxByOrNull { it.value.size }!!.key
}

fun main(args: Array<String>) {
    val arrCount = readLine()!!.trim().toInt()

    val arr = Array<Int>(arrCount, { 0 })
    for (i in 0 until arrCount) {
        val arrItem = readLine()!!.trim().toInt()
        arr[i] = arrItem
    }

    val result = mostFrequent(arr)

    println(result)
}
