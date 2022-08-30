import java.io.*
import kotlin.Throws
import kotlin.jvm.JvmStatic
import java.lang.Exception
import java.lang.RuntimeException

internal object Result {
    /*
     * Complete the 'parseInteger' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    fun parseInteger(s: String): Int {
        if (s.isBlank()) return 0
        val flag = if (s[0] == '-') -1 else 1
        val startIndex = if (s[0] == '-') 1 else 0

        var result = 0
        for(i in startIndex until s.length) {
            val char = s[i]
            if (char.isWhitespace()) continue
            if (!char.isDigit()) throw RuntimeException("Invalid input. Number can't be parsed")

            val number = (char - '0')
            result = result * 10 + number
        }
        return result * flag
    }
}

object Solution {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val bufferedReader = BufferedReader(InputStreamReader(System.`in`))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))
        val s = bufferedReader.readLine()
        try {
            val result = Result.parseInteger(s)
            bufferedWriter.write(result.toString())
            bufferedWriter.newLine()
        } catch (e: Exception) {
            bufferedWriter.write(e.message)
            bufferedWriter.newLine()
        } finally {
            bufferedReader.close()
            bufferedWriter.close()
        }
    }
}