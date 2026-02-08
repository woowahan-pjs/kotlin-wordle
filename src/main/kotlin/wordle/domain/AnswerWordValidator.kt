package wordle.domain

class AnswerWordValidator(
    private val maxLength: Int = WordRules.MAX_LENGTH,
    private val wordRegex: Regex = WordRules.WORD_REGEX,
) : WordValidator {
    override fun validate(input: String) {
        require(input.length == maxLength) { "입력값은 ${maxLength}글자여야 합니다." }

        require(wordRegex.matches(input)) { "입력값은 영어여야 합니다." }
    }
}
