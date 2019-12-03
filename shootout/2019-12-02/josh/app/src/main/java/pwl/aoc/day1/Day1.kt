package pwl.aoc.day1

import pwl.aoc.AoCDay
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Day 1 for 2019 Advent of Code.
 */
class Day1: AoCDay {
    override fun doTheDay(): Any {
        val part1 = part1()
        val part2 = part2()

        return "Part 1: $part1\nPart 2: $part2"
    }

    private fun part1(): Int {
        var total = 0
        for (mass in Input.INPUT) {
            total += calculate(mass)
        }
        return total
    }

    private fun part2(): Int {
        var total = 0
        for (mass in Input.INPUT) {
            var increment = calculate(mass)
            var toAdd = increment
            while (toAdd > 0) {
                toAdd = max(calculate(toAdd), 0)
                increment += toAdd
            }
            total += increment
        }
        return total
    }

    private fun calculate(mass: Int):  Int {
        return (floor(mass / 3.0) - 2).roundToInt()
    }
}