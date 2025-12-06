

fun Int.divisors(): List<Int> = (2..this).filter { this % it == 0 }

fun main() {
    fun part1(input: String): Long =
        input
            .split(",")
            .map { it.split("-") }
            .flatMap { it.first().toLong()..it.last().toLong() }
            .sumOf { id ->
                var sum = 0L
                id.toString().let { idString ->
                    if (idString.length.isEven()) {
                        val half = idString.length / 2
                        if (idString.take(half) == idString.takeLast(half)) {
                            sum += id
                        }
                    }
                }
                sum
            }

    fun part2(input: String): Long =
        input
            .split(",")
            .map { it.split("-") }
            .flatMap { it.first().toLong()..it.last().toLong() }
            .sumOf { id ->
                val idString = id.toString()
                idString.length.divisors().forEach { divisor ->
                    idString.chunked(idString.length / divisor).let { chunks ->
                        if (chunks.size > 1 && chunks.all { it == chunks.first() }) {
                            return@sumOf id
                        }
                    }
                }
                0L
            }

    val testInput = readInput("Day02_test")
    part1(testInput.first()).also {
        println(it)
        check(it == 1227775554L) { "Value $it was incorrect!" }
    }
    part2(testInput.first()).also {
        println(it)
        check(it == 4174379265L) { "Value $it was incorrect!" }
    }

    val input = readInput("Day02")
    part1(input.first()).println()
    part2(input.first()).println()
}
