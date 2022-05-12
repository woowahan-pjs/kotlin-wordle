package wordle

class GameWord(private val word: String) {

    init {
        require(word.length == 5) { "단어의 길이는 5글자여야 합니다." }
        require(Regex("[a-zA-Z]*").matches(word)) { "단어에 영어가 아닌 글자나 공백이 포함될 수 없습니다." }
    }
}
