package hackerrank

import java.math.BigInteger

fun fib(n: Int): String {
    return when (n) {
        0 -> 0L
        1 -> 1L
        else -> {
            var pre2 = 1L
            var pre1 = 1L
            for (i in 2..n) {
                val temp: Long = pre2 + pre1
                pre2 = pre1
                pre1 = temp
            }
            pre1
        }
    }.toString()
}

fun fibBigInteger(n: Int): BigInteger {
    return when (n) {
        0 -> 0.toBigInteger()
        1 -> 1.toBigInteger()
        else -> {
            var pre2 = 1.toBigInteger()
            var pre1 = 1.toBigInteger()
            for (i in 2..n) {
                val temp = pre2 + pre1
                pre2 = pre1
                pre1 = temp
            }
            pre1
        }
    }
}

fun main(args: Array<String>) {
    println(fib(1001))
    println(fibBigInteger(1001))
    println(Long.MAX_VALUE)
}
