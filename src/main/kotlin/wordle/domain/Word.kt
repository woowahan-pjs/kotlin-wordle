package wordle.domain

data class Word(val value: String) {

    init {
        require(isRightSize()) { "[ERROR] 5글자의 단어를 입력하세요." }
        require(isAlphabet()) { "[ERROR] 영어 단어를 입력하세요." }
    }

    private fun isRightSize(): Boolean {
        return value.length == SIZE
    }

    private fun isAlphabet(): Boolean {
        return Regex("^[a-zA-Z]*$").matches(value);
    }

    companion object {
        private const val SIZE = 5
    }
}