package wordle.domain

/**
 * 입력된 단어의 유효성 체크하는 검증기
 */
class WordleWordValidator(
    private val wordBook: WordBook,
    private val maxLength: Int = WordRules.MAX_LENGTH,
    private val wordRegex: Regex = WordRules.WORD_REGEX,
) : WordValidator {
    /**
     * 유효성 검증을 합니다.
     * - [maxLength] 글자가 맞는지 확인합니다.
     */
    override fun validate(input: String) {
        require(input.length == maxLength) { "입력값은 ${maxLength}글자여야 합니다." }

        require(wordRegex.matches(input)) { "입력값은 영어여야 합니다." }

        require(wordBook.exists(input)) { "입력값은 단어장에 존재하는 단어여야합니다." }
    }
}
