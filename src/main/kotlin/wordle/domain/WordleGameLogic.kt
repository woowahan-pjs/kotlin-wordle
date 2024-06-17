package wordle.domain

class WordleGameLogic(private val todayWord: Word) {
    fun compare(answerWord: Word): WordResult =
        todayWord.comparator()
            .matchCorrect(answerWord)
            .matchPresent((answerWord))
            .result()

    private fun Word.comparator(): WordComparator = WordComparator(this)
}
