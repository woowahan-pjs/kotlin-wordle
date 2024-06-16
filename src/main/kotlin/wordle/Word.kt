package wordle

/**
 * 사용자의 검증된 입력 단어
 */
@JvmInline
value class Word(val value: String) {

    companion object {
        private val englishRegex = Regex("^[A-Za-z]*")

        fun fromInput(input: String, checkInDic: (s: String) -> Boolean): Word {
            require(input.matches(englishRegex)) { "영문만 입력해야합니다." }
            require(input.length == 5) { "5글자여야 합니다." }
            require(checkInDic(input)) { "존재하지 않는 단어입니다." }
            return Word(input.lowercase())
        }
    }

}
