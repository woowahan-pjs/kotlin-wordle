package wordle.domain

data class Word(val value: String) {

    init {
        require(isWordSizeAndAlphabet()) {
            WRONG_WORD_SIZE_MESSAGE
        }
    }

    private fun isWordSizeAndAlphabet() = value.length == WORD_SIZE && ALPHABET_REGEX.matches(value)

    fun findAlphabet(index: Int): String {
        if (index < FIRST_INDEX || index > value.length) {
            throw IllegalArgumentException(INDEX_OUT_RANGE_MESSAGE)
        }

        return value[index].toString()
    }

    fun contains(compareValue: String): Boolean {
        return value.contains(compareValue)
    }

    companion object {
        private const val FIRST_INDEX = 0
        private const val WORD_SIZE = 5
        private const val WRONG_WORD_SIZE_MESSAGE = "5글자여야합니다"
        private const val INDEX_OUT_RANGE_MESSAGE = "인덱스 범위를 초과했습니다."
        val ALPHABET_REGEX = Regex("^[a-zA-Z]*$")
    }
}
