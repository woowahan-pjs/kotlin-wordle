package edu.nextstep.wordle.application.wordle

sealed class WordleAnswer(
    val wordle: Wordle,
) {
    class Right(wordle: Wordle) : WordleAnswer(wordle)
    class Retry(wordle: Wordle, val message: String) : WordleAnswer(wordle)
}
