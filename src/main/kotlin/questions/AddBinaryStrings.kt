
fun addBinary(a: String?, b: String?): String {
    if (a == null || b == null) return ""
    var first = a.length - 1
    var second = b.length - 1
    val sb = StringBuilder()
    var carry = 0
    while (first >= 0 || second >= 0) {
        var sum = carry
        if (first >= 0) {
            sum += a[first] - '0'
            first--
        }
        if (second >= 0) {
            sum += b[second] - '0'
            second--
        }
        carry = sum shr 1
        sum = sum and 1
        sb.append(if (sum == 0) '0' else '1')
    }
    if (carry > 0) sb.append('1')
    sb.reverse()
    return sb.toString()
}

fun main(args: Array<String>) {
    val a = readLine()!!

    val b = readLine()!!

    val result = addBinary(a, b)

    println(result)
}
