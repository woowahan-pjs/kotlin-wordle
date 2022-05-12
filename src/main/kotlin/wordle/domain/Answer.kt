package wordle.domain

class Answer(private val answer: String) {

    init {
        require(answer.length == 5) { "[ERROR] 부적절한 글자 길이입니다." }
        require(Words.contains(answer)) { "[ERROR] 목록에 존재하지 않는 단어입니다." }
    }
}
