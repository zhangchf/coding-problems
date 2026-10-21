package leetcode

fun wordSearch(board: Array<CharArray>, word: String): Boolean {
    fun pairNextChar(board: Array<CharArray>, word: String, row: Int, col: Int, charIndex: Int, visited: List<Pair<Int, Int>>): Boolean {
        if (charIndex == word.length) {
            return true
        }
        if (row < 0 || row >= board.size || col < 0 || col >= board[0].size ) {
            return false
        }
        if (visited.contains(Pair(row, col))) {
            return false
        }
        if (board[row][col] != word[charIndex]) {
            return false
        }
        val newVisited = visited + Pair(row, col)
        if (pairNextChar(board, word, row-1, col, charIndex+1, newVisited)) return true
        if (pairNextChar(board, word, row+1, col, charIndex+1, newVisited)) return true
        if (pairNextChar(board, word, row, col-1, charIndex+1, newVisited)) return true
        if (pairNextChar(board, word, row, col+1, charIndex+1, newVisited)) return true
        return false
    }

    for ((r, row) in board.withIndex()) {
        for ((c, _) in row.withIndex()) {
            if (pairNextChar(board, word, r, c, 0, listOf())) {
                return true
            }
        }
    }

    return false
}

fun main() {
//    println(wordSearch(arrayOf(
//        charArrayOf('A', 'B', 'C', 'E'),
//        charArrayOf('S', 'F', 'C', 'S'),
//        charArrayOf('A', 'D', 'E', 'E')
//    ), "ABCCED"))
//
//    println(wordSearch(arrayOf(charArrayOf('a')), "a"))

    println(wordSearch(arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    ), "ABCB"))

}