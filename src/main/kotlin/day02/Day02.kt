package day02

import utils.readInput

fun main() {
    val inputLine = readInput("day02", false)[0]
    val debug = false
    println("Part 1: answer: ${p1(inputLine, debug)}")

    println("Part 2: answer: ${p2(inputLine, debug)}")
}

private fun p2(inputLine: String, debug: Boolean): Long {
    TODO()
}

private fun p1(inputLine: String, debug: Boolean): Long {
    val ranges = inputLine.split(",")
    var total = 0L
    ranges.forEach { range ->
        val lbUb = range.split("-")
        val lb = lbUb[0].trim().toLong()
        val ub = lbUb[1].trim().toLong()

        for (i in lb..ub) {
            val id = i.toString()
            if (id.length % 2 == 0) {
                val part1 = id.take(id.length / 2)
                val part2 = id.substring(id.length / 2)
                if (part1 == part2) {
                    total += i
                }
            }
        }
    }
    return total
}
