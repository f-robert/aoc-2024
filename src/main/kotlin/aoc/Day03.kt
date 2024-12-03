package aoc

fun main() {
    val mulRegex = "mul\\((\\d+),(\\d+)\\)".toRegex()

    fun part1(input: List<String>): Int {
        var result = 0

        for (line in input) {
            result += mulRegex
                .findAll(line)
                .map {
                    val (a, b) = it.destructured
                    a.toInt() * b.toInt()
                }
                .sum()
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val re = "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)".toRegex()
        var result = 0
        var active = true

        for (s in input) {
            for (match in re.findAll(s)) {
                when (match.value) {
                    "do()" -> active = true
                    "don't()" -> active = false
                    else -> if (active) {
                        val (a, b) = match.destructured
                        result += a.toInt() * b.toInt()
                    }
                }
            }
        }

        return result
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput).also { println(it) } == 161)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
