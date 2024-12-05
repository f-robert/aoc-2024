package aoc

fun main() {
    fun parse(input: List<String>): Pair<Map<Int, List<Int>>, List<List<Int>>> {
        val rules = mutableMapOf<Int, MutableList<Int>>()
        val updates = mutableListOf<List<Int>>()
        var isRules = true

        for (line in input) {
            when {
                line.isEmpty() -> isRules = false
                isRules -> {
                    val (first, second) = line.split("|").map(String::toInt)
                    rules.getOrPut(first) { mutableListOf() }.add(second)
                }
                else -> {
                    val pages = line.split(",").map(String::toInt)
                    updates.add(pages)
                }
            }
        }

        return rules to updates
    }

    fun part1(input: List<String>): Int {
        val (rules, updates) = parse(input)

        return updates.sumOf { update ->
            if (update.zipWithNext().all { (a, b) -> rules[a]?.contains(b) == true }) update[update.size / 2] else 0
        }
    }

    fun part2(input: List<String>): Int {
        val (rules, updates) = parse(input)

        return updates
            .filterNot { update ->
                update.zipWithNext().all { (a, b) ->
                    rules[a]?.contains(b) == true
                }
            }.sumOf { update ->
                val correct = update.sortedWith { a, b ->
                    rules[a]?.indexOf(b) ?: -1
                }
                correct[correct.size / 2]
            }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput).also { println(it) } == 143)
    check(part2(testInput).also { println(it) } == 123)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
