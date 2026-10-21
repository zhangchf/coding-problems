package leetcode

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int) {
    val left: TreeNode? = null
    val right: TreeNode? = null
}

fun averageOfSubtree(root: TreeNode?): Int {
    return traverseTree(root).resultNodes
}

data class NodeInfo(
    val totalNodes: Int,
    val sum: Int,
    val resultNodes: Int
)

fun traverseTree(root: TreeNode?): NodeInfo {
    if (root == null) return NodeInfo(0, 0, 0)

    if (root.left == null && root.right == null) {
        return NodeInfo(1, root.`val`, 1)
    }
    val leftNodeInfo = traverseTree(root.left)
    val rightNodeInfo = traverseTree(root.right)
    val sum = root.`val` + leftNodeInfo.sum + rightNodeInfo.sum
    val count = 1 + leftNodeInfo.totalNodes + rightNodeInfo.totalNodes
    val isEqual = (sum/count) == root.`val`
    val resultNodes = if (isEqual) 1 else 0 + leftNodeInfo.resultNodes + rightNodeInfo.resultNodes
    return NodeInfo(count, sum, resultNodes)
}