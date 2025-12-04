package day04

import utils.readInput

fun main() {
    val debug = false
    val paperRolls = readInput("day04", false)
    val matrix: Array<BooleanArray> = paperRollArray(paperRolls)

    println("Part 1: answer: ${p1(matrix, debug).first}")
    println("Part 2: answer: ${p2(matrix, debug)}")
}


private fun p2(matrix: Array<BooleanArray>, debug: Boolean): Int {
    var prevResult = -1
    var result = 0
    var tempMatrix = matrix
    do {
        val p1ResultPair = p1(tempMatrix, debug)
        prevResult = p1ResultPair.first
        result += p1ResultPair.first
        tempMatrix = p1ResultPair.second
    } while (prevResult > 0)

    return result
}

private fun p1(matrix: Array<BooleanArray>, debug: Boolean): Pair<Int, Array<BooleanArray>> {
    var result = 0
    val newMatrix = Array(matrix.size) { BooleanArray(matrix[0].size) }
    for (i in 0 until matrix.size) {
        val cols = matrix[i]
        for (j in 0 until cols.size) {
            if (cols[j]) {
                if ((i == 0 || i == matrix.size - 1) && (j == 0 || j == cols.size - 1)) {
                    // any of the four corners
                    result++
                    newMatrix[i][j] = false
                } else {
                    when (i) {
                        0 -> {
                            // top row (not corner)
                            listOf(
                                cols[j - 1],
                                cols[j + 1],
                                matrix[i + 1][j - 1],
                                matrix[i + 1][j],
                                matrix[i + 1][j + 1]
                            ).count { it }
                        }

                        matrix.size - 1 -> {
                            // bottom row (not corner)
                            listOf(
                                cols[j - 1],
                                cols[j + 1],
                                matrix[i - 1][j - 1],
                                matrix[i - 1][j],
                                matrix[i - 1][j + 1]
                            ).count { it }
                        }

                        else -> {
                            // middle rows
                            when (j) {
                                0 -> {
                                    // left column (not corner)
                                    listOf(
                                        cols[j + 1],
                                        matrix[i - 1][j],
                                        matrix[i - 1][j + 1],
                                        matrix[i + 1][j],
                                        matrix[i + 1][j + 1]
                                    ).count { it }
                                }

                                cols.size - 1 -> {
                                    // right column (not corner)
                                    listOf(
                                        cols[j - 1],
                                        matrix[i - 1][j - 1],
                                        matrix[i - 1][j],
                                        matrix[i + 1][j - 1],
                                        matrix[i + 1][j]
                                    ).count { it }
                                }

                                else -> {
                                    // middle cells
                                    listOf(
                                        cols[j - 1],
                                        cols[j + 1],
                                        matrix[i - 1][j - 1],
                                        matrix[i - 1][j],
                                        matrix[i - 1][j + 1],
                                        matrix[i + 1][j - 1],
                                        matrix[i + 1][j],
                                        matrix[i + 1][j + 1]
                                    ).count { it }
                                }
                            }
                        }
                    }.let {
                        if (it < 4) {
                            result++
                            newMatrix[i][j] = false
                        } else {
                            newMatrix[i][j] = true
                        }
                    }
                }
            } else {
                newMatrix[i][j] = false
            }
        }
    }

    return Pair(result, newMatrix)
}

private fun paperRollArray(paperRolls: List<String>): Array<BooleanArray> {
    val rows = paperRolls.size
    val cols = paperRolls[0].length
    val matrix = Array(rows) { BooleanArray(cols) }

    for (i in 0 until rows) {
        for (j in 0 until cols) {
            matrix[i][j] = paperRolls[i][j] == '@'
        }
    }

    return matrix
}
