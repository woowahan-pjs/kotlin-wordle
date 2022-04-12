package wordle.domain

data class Word(val value: String) {

    init {
        require(value.length == WORD_SIZE && regex.matches(value)) {
            throw IllegalArgumentException(WRONG_WORD_SIZE_MESSAGE)
        }
    }

    fun foundAlphabet(index: Int): String {
        if (index < FIRST_INDEX || index > value.length) {
            throw IllegalArgumentException(INDEX_OUT_RANGE_MESSAGE)
        }

        return value[index].toString()
    }

    companion object {
        const val FIRST_INDEX = 0
        const val WORD_SIZE = 5
        const val WRONG_WORD_SIZE_MESSAGE = "5글자여야합니다"
        const val INDEX_OUT_RANGE_MESSAGE = "인덱스 범위를 초과했습니다."
        val regex = Regex("^[a-zA-Z]*$")
    }
}
