package wordle.domain

data class Word(val word: String) {
    init {
        check(word.isNotBlank()) { "단어는 공백만 입력할 수 없습니다" }
        check(word.length == WORD_LENGTH) { "단어의 길이는 5자 입니다." }
    }
}

const val WORD_LENGTH = 5
