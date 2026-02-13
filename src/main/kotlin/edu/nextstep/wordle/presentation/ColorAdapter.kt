package edu.nextstep.wordle.presentation

import edu.nextstep.wordle.application.wordle.window.Match

enum class ColorAdapter(val match: Match, val tile: String) {
    PERFECT(Match.PERFECT, "\uD83D\uDFE9"),
    WRONG(Match.WRONG, "\uD83D\uDFE8"),
    MISS(Match.MISS, "⬜"),
    ;

    companion object {
        fun convert(match: Match): String {
            return when (match) {
                Match.PERFECT -> PERFECT.tile
                Match.WRONG -> WRONG.tile
                Match.MISS -> MISS.tile
            }
        }
    }
}
