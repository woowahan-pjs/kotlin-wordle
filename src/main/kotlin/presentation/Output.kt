package presentation

import domain.Game
import domain.MatchResult
import domain.MatchResults

fun printAll(game: Game) {
    game.allMatchResults.forEach(::printLine)
}

private fun printLine(matchResults: MatchResults) {
    println(matchResults.results.joinToString(BLANK) { it.toBlock() })
}

private fun MatchResult.toBlock(): String = when (this) {
    MatchResult.GREEN -> "\uD83D\uDFE9"
    MatchResult.YELLOW -> "\uD83D\uDFE8"
    else -> "⬜"
}

private const val BLANK = ""
