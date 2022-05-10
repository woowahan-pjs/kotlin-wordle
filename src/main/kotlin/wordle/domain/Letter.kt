package wordle.domain

data class Letter(private val value: Char) {

    init {
        require(value.isLowerCase()) { "글자는 알파벳 소문자여야합니다." }
    }
}