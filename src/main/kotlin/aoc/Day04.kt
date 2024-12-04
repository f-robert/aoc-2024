package aoc

fun main() {
    fun words(input:List<String>) = sequence<String> {
        for (y in 0..<input.size) {
            for (x in 0..<input[0].length - 3) {
                val word = input[y].substring(x, x + 4)
                yield(word)
                yield(word.reversed())
            }
        }

        for (y in 0..<input.size - 3) {
            for (x in 0..<input[0].length) {
                val word = listOf(
                    input[y][x],
                    input[y + 1][x],
                    input[y + 2][x],
                    input[y + 3][x]
                ).let { String(it.toCharArray()) }
                yield(word)
                yield(word.reversed())
            }
        }

        for (y in 0..<input[0].length - 3) {
            for (x in 0..<input.size - 3) {
                val word = listOf(
                    input[y][x],
                    input[y + 1][x + 1],
                    input[y + 2][x + 2],
                    input[y + 3][x + 3]
                ).let { String(it.toCharArray()) }
                yield(word)
                yield(word.reversed())
            }
        }

        for (y in 0..<input[0].length - 3) {
            for (x in 0..<input.size - 3) {
                val word = listOf(
                    input[y][x + 3],
                    input[y + 1][x + 2],
                    input[y + 2][x + 1],
                    input[y + 3][x]
                ).let { String(it.toCharArray()) }
                yield(word)
                yield(word.reversed())
            }
        }
    }

    fun words2(input:List<String>) = sequence<String> {
        for (y in 0..<input.size - 2) {
            for (x in 0..<input[0].length - 2) {
                val word = listOf(
                    input[y][x],
                    input[y][x + 2],
                    input[y + 1][x + 1],
                    input[y + 2][x],
                    input[y + 2][x + 2],
                ).let { String(it.toCharArray()) }
                yield(word)
            }
        }
    }

    fun part1(input: List<String>) = words(input)
        .filter { it == "XMAS" }
        .count()

    fun part2(input: List<String>) = words2(input)
        .filter { it in listOf("MSAMS", "SMASM", "MMASS", "SSAMM") }
        .count()

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
