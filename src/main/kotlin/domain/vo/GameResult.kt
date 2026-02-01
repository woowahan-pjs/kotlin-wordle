package domain.vo

import domain.Color

data class GameResult(
    val emojiArray: List<Color>,
    val isSuccess: Boolean,
)