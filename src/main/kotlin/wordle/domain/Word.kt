package wordle.domain

import java.util.regex.Pattern

data class Word(val value: String) {

    init {
        require(isRightSize()) { "[ERROR] 5글자의 단어를 입력하세요." }
        require(isAlphabet()) { "[ERROR] 영어 단어를 입력하세요." }
    }

    private fun isRightSize(): Boolean = value.length == SIZE

    private fun isAlphabet(): Boolean = Pattern.matches("^[a-zA-Z]*$", value)

    fun sameIndexAndSpell(index: Int, spell: Char): Boolean = value[index] == spell

    fun contains(spell: Char): Boolean = value.contains(spell)

    companion object {
        private const val SIZE = 5
    }
}
