package edu.nextstep.wordle.application.wordle

import edu.nextstep.wordle.application.wordle.window.Match
import edu.nextstep.wordle.application.wordle.window.WindowResult

data class WordResult(
    val round: Int,
    val windowResults: List<WindowResult>,
) {
    fun isSuccess(): Boolean {
        return this.windowResults.map { it.match }
            .all { it == Match.PERFECT }
    }
}
