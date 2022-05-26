package wordle.domain

data class Word(val value: String) {

    init {
        require(isRightSize()) { "[ERROR] ${SIZE}글자의 단어를 입력하세요." }
        require(isAlphabet()) { "[ERROR] 영어 단어를 입력하세요." }
    }

    private fun isRightSize(): Boolean = value.length == SIZE

    private fun isAlphabet(): Boolean = Regex("^[a-zA-Z]*$").matches(value)

    fun sameIndexAndSpell(index: Int, spell: Char): Boolean = value[index] == spell

    fun contains(spell: Char): Boolean = value.contains(spell)

    companion object {
        const val SIZE = 5
    }
}
