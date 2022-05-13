package wordle.domain

data class Word(val value: String) {

    init {
        require(value.length == 5) { "단어의 길이는 5글자여야 합니다." }
        require(Regex("[a-zA-Z]*").matches(value)) { "단어에 영어가 아닌 글자나 공백이 포함될 수 없습니다." }
    }

    fun isSameChar(other: Word, index: Int): Boolean {
        return this.value[index] == other.value[index]
    }
}
