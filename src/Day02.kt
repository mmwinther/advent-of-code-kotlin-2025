import java.math.BigInteger

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
            .map { it.first().toLong()..it.last().toLong() }
            .sumOf { idRange ->
                idRange.sumOf { id ->
                    var sum = 0L
                    id.toString().let { idString ->
                        idString.length.divisors().let { divisors ->
                            for (divisor in divisors) {
                                idString.windowed(idString.length / divisor, idString.length / divisor, partialWindows = false).let { chunks ->
                                    if (chunks.size > 1 && chunks.all { it == chunks.first() }) {
                                        sum += id
                                        break
                                    }
                                }
                            }
                        }
                    }
                    sum
                }
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
