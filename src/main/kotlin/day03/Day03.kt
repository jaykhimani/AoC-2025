package day03

import utils.log
import utils.readInput

fun main() {
    val batteryBanks = readInput("day03", true)
    val debug = false
    println("Part 1: answer: ${p1(batteryBanks, debug)}")

//    println("Part 2: answer: ${p2(batteryBanks, debug)}")
}

private fun p2(batteryBanks: List<String>, debug: Boolean): Long {
    TODO()
}

private fun p1(batteryBanks: List<String>, debug: Boolean): Long {
    var totalJolt = 0L
    batteryBanks.forEach { bank ->
        var currentMaxJolt = -1
        log("Evaluating bank: $bank", debug)
        // TODO: Very expensive loop, optimize time complexity (later)
        for (i in bank.indices) {
            for (j in i + 1 until bank.length) {
                val currentJolt = (bank[i].digitToInt() * 10) + bank[j].digitToInt()
                log("Current jolt: $currentJolt", debug)
                if (currentJolt > currentMaxJolt) {
                    currentMaxJolt = currentJolt
                }
            }
        }
        totalJolt += currentMaxJolt
    }


    return totalJolt
}
