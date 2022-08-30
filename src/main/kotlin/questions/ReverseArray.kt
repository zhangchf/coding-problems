/*
 * Complete the 'reverseArray' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts INTEGER_ARRAY a as parameter.
 */

fun reverseArray(a: Array<Int>): Array<Int> {
    // Write your code here

//    val size = a.size
//    for (i in 0 until size/2) {
//        val tmp = a[i]
//        a[i] = a[size - 1 - i]
//        a[size - 1 - i] = tmp
//    }
//    return a

    return Array(a.size) { i -> a[i] }

}

fun main() {
    val arrCount = readLine()!!.trim().toInt()

    val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val res = reverseArray(arr)

    println(res.joinToString(" "))
}
