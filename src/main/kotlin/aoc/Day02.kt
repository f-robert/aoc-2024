package aoc

import java.util.*
import kotlin.math.abs

fun main() {
    fun String.parse() = split(" ").map(String::toInt)

    fun Iterable<Int>.safe() = zipWithNext()
        .map { (a, b) -> abs(a - b) }
        .all { it in 1..3 }

    fun Iterable<Int>.monotonic() = zipWithNext().all { (a, b) -> a < b }
            || zipWithNext().all { (a, b) -> a > b }

    fun part1(input: List<String>) = input
        .map(String::parse)
        .count { it.safe() && it.monotonic() }

    fun part2(input: List<String>): Int {
        var number = 0

        for (line in input) {
            val l = LinkedList(line.parse())

            for (i in -1..<l.size) {
                val levels = LinkedList(l)
                if (i >= 0) {
                    levels.removeAt(i)
                }

                if (levels.safe() && levels.monotonic()) {
                    number++
                    break
                }
            }
        }

        return number
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput).also { println(it) } == 2)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
