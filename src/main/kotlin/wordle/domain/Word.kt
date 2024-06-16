package wordle.domain

/**
 * 사용자의 검증된 입력 단어
 */
@JvmInline
value class Word(val value: String) {

    companion object {
        private val englishRegex = Regex("^[A-Za-z]*")

        fun fromInput(input: String): Word {
            require(input.matches(englishRegex)) { "영문만 입력해야합니다." }
            require(input.length == 5) { "5글자여야 합니다." }
            return Word(input.lowercase())
        }
    }

}
