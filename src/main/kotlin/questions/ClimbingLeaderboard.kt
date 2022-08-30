package questions

import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*

/*
 * Complete the 'climbingLeaderboard' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY ranked
 *  2. INTEGER_ARRAY player
 */

fun climbingLeaderboard(ranked: Array<Int>, player: Array<Int>): Array<Int> {
    // Write your code here

    val result = Array(player.size) { 0 }

    var i = 0
    var j = player.size - 1
    var rank = 0
    var rankScore = Int.MAX_VALUE
    while (j >= 0 && i < ranked.size) {
        if (player[j] >= ranked[i]) {
            rank++
            rankScore = ranked[i]
            result[j] = rank
            j--
        } else {
            if (ranked[i] < rankScore) {
                rank++
                rankScore = ranked[i]
            }
            i++
        }
    }
    while (j >= 0) {
        result[j] = rank + 1
        j--
    }
    return result

}

fun main(args: Array<String>) {
    val rankedCount = readLine()!!.trim().toInt()

    val ranked = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val playerCount = readLine()!!.trim().toInt()

    val player = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val result = climbingLeaderboard(ranked, player)

    println(result.joinToString("\n"))
}
