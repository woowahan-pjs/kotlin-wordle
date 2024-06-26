package wordle.view

import wordle.domain.LetterMatch

enum class Tile(val matchType: LetterMatch, val color: String) {
    GREEN(LetterMatch.CORRECT, "🟩"),
    YELLOW(LetterMatch.PRESENT, "🟨"),
    GREY(LetterMatch.ABSENT, "⬜"),
    ;

    companion object {
        fun of(matchType: LetterMatch): Tile = entries.find { tile -> tile.matchType == matchType } ?: GREY
    }
}
