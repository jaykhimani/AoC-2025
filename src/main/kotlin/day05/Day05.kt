package day05

import utils.log
import utils.readInput

fun main() {
    val debug = false
    val lines = readInput("day05", false)

    println("Part 1: answer: ${p1(lines, debug)}")
    println("Part 2: answer: ${p2(lines, debug)}")
}


private fun p2(lines: List<String>, debug: Boolean): Long {
    var total = 0L
    val freshRanges = lines.takeWhile { it.isNotBlank() }
        .map { it.split("-") }
        .map { LongRange(it[0].trim().toLong(), it[1].trim().toLong()) }
        .toMutableList()
        .sortedBy { it.first }

    log("Filtered ranges: $freshRanges", debug)

    val sortedRanges = freshRanges.sortedBy { it.first }

    var mergedStart = sortedRanges[0].first
    var mergedEnd = sortedRanges[0].last

    for (i in 1 until sortedRanges.size) {
        val range = sortedRanges[i]
        if (range.first <= mergedEnd + 1) {
            mergedEnd = maxOf(mergedEnd, range.last)
        } else {
            total += (mergedEnd - mergedStart + 1)
            mergedStart = range.first
            mergedEnd = range.last
        }
    }
    total += (mergedEnd - mergedStart + 1)

    return total
}


private fun p1(lines: List<String>, debug: Boolean): Int {
    val blankLineIndex = lines.indexOfFirst { it.isBlank() }

    val ranges = lines.take(blankLineIndex)
        .map { it.split("-") }
        .map { LongRange(it[0].trim().toLong(), it[1].trim().toLong()) }

    val items = lines.drop(blankLineIndex + 1)
        .map { it.toLong() }

    log("Ranges: $ranges", debug)

    return items.count { item -> ranges.any { item in it } }
}

