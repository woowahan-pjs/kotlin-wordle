package presentation

import domain.MatchResult
import domain.MatchResults
import domain.Output

object DefaultOutput : Output {
    private const val BLANK = ""
    private const val MAX_TRY_COUNT = 6

    private val allResults: MutableList<MatchResults> = mutableListOf()

    override fun write(matchResults: MatchResults) {
        allResults.add(matchResults)

        if (matchResults.isCorrect()) {
            println("${allResults.size}/$MAX_TRY_COUNT")
        }

        printAll()
    }

    private fun printAll() {
        allResults.forEach {
            printLine(it)
        }
    }

    private fun printLine(matchResults: MatchResults) {
        println(
            matchResults.results.joinToString(BLANK) {
                blockOf(it)
            }
        )
    }

    private fun blockOf(matchResult: MatchResult): String = when (matchResult) {
        MatchResult.CORRECT -> "\uD83D\uDFE9"
        MatchResult.MISSING -> "\uD83D\uDFE8"
        else -> "⬜"
    }
}
