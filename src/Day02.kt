import java.math.BigInteger

fun main() {
    fun part1(input: String): BigInteger =
        input
            .split(",")
            .map { it.split("-") }
            .sumOf { idRange ->
                var sum: BigInteger = 0.toBigInteger()
                var id: BigInteger = idRange.first().toBigInteger()
                while (id <= idRange.last().toBigInteger()) {
                    id.toString().let { idString ->
                        if (idString.length.isEven()) {
                            val half = idString.length / 2
                            if (idString.take(half) == idString.takeLast(half)) {
                                sum += id
                            }
                        }
                        id++
                    }
                }
                sum
            }

//    fun part2(input: List<String>): Int = 0

    val testInput = readInput("Day02_test")
    part1(testInput.first()).also {
        println(it)
        assert(it == 1227775554.toBigInteger()) { "Value $it was incorrect!" }
    }
//    part2(testInput).also { assert(it == 6) { "Value $it was incorrect!" } }

    val input = readInput("Day02")
    part1(input.first()).println()
//    part2(input).println()
}
