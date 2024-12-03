package aoc

import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        for (line in input) {
            val (l, r) = line.split("\\s+".toRegex())
            left.add(l.toInt())
            right.add(r.toInt())
        }

        left.sort()
        right.sort()

        var distance = 0
        for (i in 0 until left.size) {
            distance += abs(right[i] - left[i])
        }

        return distance
    }

    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        for (line in input) {
            val (l, r) = line.split("\\s+".toRegex())
            left.add(l.toInt())
            right.add(r.toInt())
        }

        val count = left.groupingBy { it }.eachCount()
        var distance = 0
        for (i in 0 until right.size) {
            distance += (count[right[i]] ?: 0) * right[i]
        }

        return distance
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
