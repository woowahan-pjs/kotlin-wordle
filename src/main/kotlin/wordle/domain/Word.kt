package wordle.domain

@JvmInline
value class Word(
    val value: String
) {
    init {
        require(value.length == 5) { "단어는 5글자만 됩니다" }
    }
}
