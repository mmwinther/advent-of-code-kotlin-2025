enum class Direction(
    val symbol: Char,
    val sign: Int,
) {
    RIGHT('R', 1),
    LEFT('L', -1),
    ;

    companion object {
        fun of(symbol: Char): Direction =
            Direction.entries.firstOrNull { it.symbol == symbol }
                ?: throw IllegalArgumentException("Could not parse direction")
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val start = 50
        val dial = 0..99
        var dialState: Int = start
        var count = 0
        input
            .map {
                Direction.of(it.first()) to
                    it
                        .slice(1..<it.length)
                        .toInt()
            }.forEach {
                dialState += it.second * it.first.sign
                while (dialState !in dial) {
                    if (dialState > dial.last) dialState -= dial.size()
                    if (dialState < dial.first) dialState += dial.size()
                }
                if (dialState == 0) count++
            }
        return count
    }

    fun part2(input: List<String>): Int {
        val start = 50
        var dialState: Int = start
        var count = 0
        input
            .map {
                Direction.of(it.first()) to
                    it.drop(1).toInt()
            }.forEach { instruction ->
                repeat(instruction.second) {
                    dialState = (dialState + instruction.first.sign) % 100
                    if (dialState == 0) count++
                }
            }
        return count
    }

    val testInput = readInput("Day01_test")
    part1(testInput).also { assert(it == 3) { "Value $it was incorrect!" } }
    part2(testInput).also { assert(it == 6) { "Value $it was incorrect!" } }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
