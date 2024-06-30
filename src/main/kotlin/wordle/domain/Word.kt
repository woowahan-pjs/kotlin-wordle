package wordle.domain

/**
 * 사용자의 검증된 입력 단어
 */
@JvmInline
value class Word private constructor(val value: String) {

    init {
        require(value.matches(englishRegex)) { "영문만 입력해야합니다." }
        require(value.length == 5) { "5글자여야 합니다." }
    }

    constructor(value: String, isLowercase: Boolean = false) :
            this(if (isLowercase) value else value.lowercase())


    companion object {
        private val englishRegex = Regex("^[A-Za-z]*")
    }

}
