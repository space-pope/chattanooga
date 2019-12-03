package pwl.aoc.day2

import pwl.aoc.AoCDay
import pwl.aoc.day1.Input
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Day 2 for 2019 Advent of Code.
 */
class Day2 : AoCDay {
    override fun doTheDay(): Any {
        val part1 = part1()
        val part2 = part2()
        return "Part 1: $part1\nPart 2: $part2"
    }

    private fun part1(): Int {
        val restored = pwl.aoc.day2.Input.INTCODE
        return reconstruct(restored, 12, 2)[0]
    }

    private fun part2(): Int {
        val restored = pwl.aoc.day2.Input.INTCODE
        val target = 19690720
        for (i in 0..99) {
            for (j in 0..99) {
                if (reconstruct(restored, i, j)[0] == target) {
                    return (100 * i) + j
                }
            }
        }
        throw Exception("proper syntax not found!")
    }

    private fun reconstruct(program: List<Int>, noun: Int, verb : Int): List<Int> {
        val result = program.toMutableList()
        result[1] = noun
        result[2] = verb
        for (idx in 0..result.size step 4) {
            val input1 = result[idx + 1]
            val input2 = result[idx + 2]
            val targetIdx = result[idx + 3]
            when (result[idx]) {
                99 -> return result
                1 -> result[targetIdx] = result[input1] + result[input2]
                2 -> result[targetIdx] = result[input1] * result[input2]
                else -> throw Exception("Unknown opcode ${result[idx]} at index $idx!")
            }
        }
        return result
    }
}