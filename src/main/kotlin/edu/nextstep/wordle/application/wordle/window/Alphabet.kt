package edu.nextstep.wordle.application.wordle.window

class Alphabet(
    value: String,
) {
    val alphabet: String = value.lowercase()

    init {
        if (!alphabets.contains(alphabet)) {
            throw IllegalArgumentException("$alphabet 알파벳 입력만 허용합니다.")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Alphabet

        if (alphabet != other.alphabet) return false

        return true
    }

    override fun hashCode(): Int {
        return alphabet.hashCode()
    }

    companion object {
        private val alphabets = ('A'..'Z').map { it.lowercase() }.toSet()
    }
}
