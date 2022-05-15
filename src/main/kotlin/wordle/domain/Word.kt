package wordle.domain

private const val WORD_SIZE = 5

class Word(_value: String) {

    val value: String = _value.lowercase()

    init {
        require(value.length == WORD_SIZE) { "단어의 길이는 5글자여야 합니다." }
        require(Regex("[a-zA-Z]*").matches(value)) { "단어에 영어가 아닌 글자나 공백이 포함될 수 없습니다." }
    }

    fun isSameChar(other: Word, index: Int): Boolean {
        return this.value[index] == other.value[index]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Word

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
