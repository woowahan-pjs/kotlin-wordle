package edu.nextstep.wordle.application.wordle.window

enum class Match {
    PERFECT,
    WRONG,
    MISS,
    ;

    fun updatable(other: Match): Boolean {
        return when (this) {
            PERFECT -> false
            WRONG -> other == PERFECT
            MISS -> other != MISS
        }
    }
}
