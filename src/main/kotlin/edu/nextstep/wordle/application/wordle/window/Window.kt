package edu.nextstep.wordle.application.wordle.window

data class Window(
    val alphabet: Alphabet,
    val position: Int,
) {
    fun match(other: Window): Match {
        val alphabetMatch = this.alphabet == other.alphabet
        val positionMatch = this.position == other.position

        return if (alphabetMatch && positionMatch) {
            Match.PERFECT
        } else if (alphabetMatch) {
            Match.WRONG
        } else {
            Match.MISS
        }
    }
}
