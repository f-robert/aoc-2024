package aoc

fun main() {
    fun antennas(input: List<String>): MutableMap<Char, MutableList<Pair<Int, Int>>> {
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()

        for (y in input.indices) {
            for (x in input[y].indices) {
                val square = input[y][x]

                if (square.isDigit() || square.isLetter()) {
                    antennas.getOrPut(square) { mutableListOf() }
                        .add(y to x)
                }
            }
        }

        return antennas
    }

    fun part1(input: List<String>): Int {
        val antennas = antennas(input)
        val antiNodes = mutableSetOf<Pair<Int, Int>>()

        for ((_, locations) in antennas) {
            val locs = locations.sortedBy { it.first * it.first + it.second * it.second }

            for (l1 in locs) {
                for (l2 in locs) {
                    if (l1 == l2) continue

                    val dy = l2.first - l1.first
                    val dx = l2.second - l1.second

                    antiNodes += l1.first - dy to l1.second - dx
                    antiNodes += l2.first + dy to l2.second + dx
                }
            }
        }

        return antiNodes
            .count {
                it.first in input.indices && it.second in input[it.first].indices
            }
    }

    fun part2(input: List<String>): Int {
        val antennas = antennas(input)
        val antiNodes = mutableSetOf<Pair<Int, Int>>()

        for ((_, locations) in antennas) {
            val locs = locations.sortedBy { it.first * it.first + it.second * it.second }

            for (l1 in locs) {
                for (l2 in locs) {
                    if (l1 == l2) continue

                    val dy = l2.first - l1.first
                    val dx = l2.second - l1.second

                    antiNodes += l1
                    antiNodes += l2

                    for (i in 1..50) {
                        val a1 = l1.first - i * dy to l1.second - i * dx
                        val a2 = l2.first + i * dy to l2.second + i * dx

                        antiNodes += a1
                        antiNodes += a2
                    }
                }
            }
        }

        return antiNodes
            .filter {
                it.first in input.indices && it.second in input[it.first].indices
            }
            .distinct()
            .size
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 14)
    check(part2(testInput) == 34)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
