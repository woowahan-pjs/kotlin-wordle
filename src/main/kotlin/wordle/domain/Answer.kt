package wordle.domain

class Answer(private val answer: String) {

    init {
        require(answer.length == 5) { "[ERROR] 부적절한 글자 길이입니다." }
//        require(Words.contains(answer)) {}
    }
}
