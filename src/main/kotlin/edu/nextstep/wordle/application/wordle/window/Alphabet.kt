package edu.nextstep.wordle.application.wordle.window

class Alphabet(
    value: String,
) {
    val _value: String = value.lowercase()

    init {
        if (!alphabets.contains(_value)) {
            throw IllegalArgumentException("$_value 알파벳 입력만 허용합니다.")
        }
    }

    companion object {
        private val alphabets = ('A'..'Z').map { it.lowercase() }.toSet()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Alphabet

        if (_value != other._value) return false

        return true
    }

    override fun hashCode(): Int {
        return _value.hashCode()
    }
}
