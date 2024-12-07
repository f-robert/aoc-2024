package aoc

enum class Direction(val symbol: Char) {
    UP('^') {
        override fun forward(x: Int, y: Int) = x to y - 1
    },
    RIGHT('>') {
        override fun forward(x: Int, y: Int) = x + 1 to y
    },
    DOWN('v') {
        override fun forward(x: Int, y: Int) = x to y + 1
    },
    LEFT('<'){
        override fun forward(x: Int, y: Int) = x - 1 to y
    };

    companion object {
        fun fromSymbol(symbol: Char) = entries.first { it.symbol == symbol }
    }

    abstract fun forward(x: Int, y: Int): Pair<Int, Int>

    fun turn() = entries[(ordinal + 1) % entries.size]
}

fun main() {
    fun part1(input: List<String>): Int {
        val map = input.map { line ->
            line.replace("[\\\\^v<>]".toRegex(), ".").toCharArray()
        }

        var x = -1
        var y = -1
        var direction = Direction.UP

        loop@ for (j in input.indices) {
            for (i in input[j].indices) {
                if (input[j][i] in "^v<>") {
                    x = i
                    y = j
                    direction = Direction.fromSymbol(input[j][i])
                    break@loop
                }
            }
        }

        val positions = mutableSetOf(x to y)

        while (true) {
            val (newX, newY) = direction.forward(x, y)
            if (newY in 0..<map.size && newX in 0..<map[y].size) {
                if (map[newY][newX] == '#') {
                    direction = direction.turn()
                } else {
                    x = newX
                    y = newY
                    positions += x to y
                }
            } else {
                break
            }
        }

        return positions.count()
    }

    fun part2(input: List<String>): Int {
        var obstructionCount = 0

        for (oy in input.indices) {
            for (ox in input.indices) {
                if (input[oy][ox] in "#^v<>") {
                    continue
                }

                val map = input.map { line ->
                    line.replace("[\\\\^v<>]".toRegex(), ".").toCharArray()
                }
                map[oy][ox] = 'O'
                var x = -1
                var y = -1
                var direction = Direction.UP

                loop@ for (j in input.indices) {
                    for (i in input[j].indices) {
                        if (input[j][i] in "^v<>") {
                            x = i
                            y = j
                            direction = Direction.fromSymbol(input[j][i])
                            break@loop
                        }
                    }
                }

                val obstructions = mutableSetOf(Triple(x, y, direction))
                var loop = false

                while (true) {
                    val (newX, newY) = direction.forward(x, y)
                    if (newY in 0..<map.size && newX in 0..<map[y].size) {
                        if (map[newY][newX] in "#O") {
                            if (Triple(newX, newY, direction) in obstructions) {
                                loop = true
                                break
                            } else {
                                obstructions += Triple(newX, newY, direction)
                            }
                            direction = direction.turn()
                        } else {
                            x = newX
                            y = newY
                        }
                    } else {
                        break
                    }
                }

                if (loop) {
                    obstructionCount++
                }
            }
        }

        return obstructionCount
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput).also { println(it) } == 41)
    check(part2(testInput).also { println(it) } == 6)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
