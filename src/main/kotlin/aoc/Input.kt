package aoc

fun readInput(name: String) = {}::class.java.classLoader.getResource("$name.txt")
    ?.readText()
    ?.trim()
    ?.lines()
    ?: error("Input $name.txt not found")
