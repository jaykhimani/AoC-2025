package day01

import utils.log
import utils.readInput

fun main() {
    val inputLines = readInput("day01")
    val debug = false
    println("Part 1: password: ${p1(inputLines, debug)}")

    println("Part 2: password: ${p2(inputLines, debug)}")
}

private fun p2(inputLines: List<String>, debug: Boolean): Int {
    var curPos = 50

    var password = 0
    inputLines.forEach { line ->
        log(line, debug)
        val directions = line[0].lowercase()

        val distance = line.substring(1).toInt()

        for (i in 0 until distance) {
            if (directions == "l") {
                curPos -= 1
            } else {
                curPos += 1
            }
            if (curPos < 0) {
                curPos = 99
            }
            if (curPos > 99) {
                curPos = 0
            }
            if (curPos == 0) {
                password += 1
            }
        }
    }
    return password

}

private fun p1(inputLines: List<String>, debug: Boolean): Int {
    var curPos = 50

    var password = 0;
    inputLines.forEach { line ->
        log("Processing $line", debug)
        val direction = line[0].lowercase()
        log("\tDirection is $direction", debug)
        val distance = line.substring(1).toInt()
        log("\tNumber is $distance", debug)

        if (direction == "l") {
            curPos -= distance
        } else {
            curPos += distance
        }

        curPos %= 100
        if (curPos < 0) {
            curPos += 100
        }

        if (curPos == 0) {
            password += 1
        }
        log("\tcurPos is $curPos\n", debug)
    }
    return password
}
