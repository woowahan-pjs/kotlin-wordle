package wordle.domain.word

class WordValidator(
    private val wordExtractor: WordExtractor,
) {
    fun validate(word: Word) {
        if (word.isBlank() || word.length() != 5) {
            throw WordValidationException("입력값은 5글자여야 합니다.")
        }

        if (!REGEX.matches(word.value)) {
            throw WordValidationException("입력값은 영어여야 합니다.")
        }

        if (!wordExtractor.exists(word)) {
            throw WordValidationException("입력값은 단어장에 존재하는 단어여야합니다.")
        }
    }

    companion object {
        private val REGEX = Regex("^[a-zA-Z]*$")
    }
}
