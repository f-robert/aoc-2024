package aoc

fun main() {
    fun part1(input: List<String>): Long {
        var sum = 0L

        for (line in input) {
            val longs = line.toLongList()
            val (expected, numbers) = longs.first() to longs.drop(1)
            val combinations = generateCombinations(listOf('+', '*'), numbers.size - 1)

            for (operator in combinations) {
                var actual = numbers[0]
                for (i in 1 until numbers.size) {
                    actual = when (operator[i - 1]) {
                        '+' -> actual + numbers[i]
                        '*' -> actual * numbers[i]
                        else -> error("Unknown operator: ${operator[i - 1]}")
                    }
                }
                if (actual == expected) {
                    sum += expected
                    break
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Long {
        val input2 = input.map { it.replace("||", "|") }
        var sum = 0L

        for (line in input2) {
            val longs = line.toLongList()
            val (expected, numbers) = longs.first() to longs.drop(1)
            val combinations = generateCombinations(listOf('+', '*', '|'), numbers.size - 1)

            for (operator in combinations) {
                var actual = numbers[0]
                for (i in 1 until numbers.size) {
                    actual = when (operator[i - 1]) {
                        '+' -> actual + numbers[i]
                        '*' -> actual * numbers[i]
                        '|' -> "$actual${numbers[i]}".toLong()
                        else -> error("Unknown operator: ${operator[i - 1]}")
                    }
                }
                if (actual == expected) {
                    sum += expected
                    break
                }
            }
        }

        return sum
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput).also { println(it) } == 3749L)
    check(part2(testInput).also { println(it) } == 11387L)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
