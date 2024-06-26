package wordle.domain

typealias AnswerWord = Word

fun AnswerWord(answerWord: String): AnswerWord {
    return Word(answerWord)
}
