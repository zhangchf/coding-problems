
fun parseInteger(s: String): Int {
    var i = 0
    var num = 0
    var isNeg = false
    val noSpaceS = s.replace("\\s".toRegex(), "")
    if (noSpaceS[0] == '-') {
        isNeg = true
        i = 1
    }
    while (i < noSpaceS.length) {
        if (i > 0 && !Character.isDigit(noSpaceS[i])) {
            throw RuntimeException("Invalid input. Number cannot be parsed.")
        }
        num *= 10
        num += noSpaceS[i++] - '0'
    }
    if (isNeg) num = -num
    return num
}

fun main(args: Array<String>) {
    val s = readLine()!!

    val result = try {
        parseInteger(s)
    } catch (e: Exception) {
        e.message
    }

    println(result)
}
