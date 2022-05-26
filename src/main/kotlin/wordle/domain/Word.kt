package wordle.domain

data class Word(val value: String) {

    init {
        require(value.isRightSize()) { "[ERROR] ${SIZE}글자의 단어를 입력하세요." }
        require(value.isAlphabet()) { "[ERROR] 영어 단어를 입력하세요." }
    }

    private fun String.isRightSize(): Boolean = value.length == SIZE

    private fun String.isAlphabet(): Boolean = Regex("^[a-zA-Z]*$").matches(value)

    fun sameIndexAndSpell(index: Int, spell: Char): Boolean = value[index] == spell

    fun contains(spell: Char): Boolean = value.contains(spell)

    companion object {
        const val SIZE = 5
    }
}
