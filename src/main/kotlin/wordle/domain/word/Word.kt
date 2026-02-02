package wordle.domain.word

data class Word(
    val value: String,
) {
    fun length(): Int = value.length

    fun isBlank(): Boolean = value.isBlank()

    fun toCharArray(): CharArray = value.toCharArray()

    fun matchesAt(
        index: Int,
        char: Char,
    ): Boolean {
        val charArray = toCharArray()
        require(charArray.size >= index) { "잘못된 인덱스 호출입니다. 인덱스 : $index" }
        return charArray[index] == char
    }
}
