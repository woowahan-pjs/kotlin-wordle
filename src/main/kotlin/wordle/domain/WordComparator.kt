package wordle.domain

class WordComparator(
    private val markerLetters: MutableList<Letter>,
    private val wordResult: WordResult = WordResult(),
) {
    fun matchCorrect(answerWord: Word): WordComparator =
        apply {
            markerLetters.forEachIndexed { index, _ -> changeCorrectMatch(index, answerWord[index]) }
        }

    fun matchPresent(answerWord: Word): WordComparator =
        apply {
            markerLetters.forEachIndexed { index, _ -> changePresentMatch(index, answerWord[index]) }
        }

    fun result(): WordResult = wordResult

    private fun changeCorrectMatch(
        index: Int,
        answerLetter: Letter,
    ) {
        if (isCorrectLetter(index, answerLetter)) {
            wordResult.changeMatchType(index, LetterMatch.CORRECT)
            changeMarkerLetter(index)
        }
    }

    private fun changePresentMatch(
        index: Int,
        answerLetter: Letter,
    ) {
        if (isPresentLatter(index, answerLetter)) {
            wordResult.changeMatchType(index, LetterMatch.PRESENT)
            changeMarkerLetterIndexOf(answerLetter)
        }
    }

    private fun isCorrectLetter(
        index: Int,
        answerLetter: Letter,
    ) = markerLetters[index] == answerLetter

    private fun isPresentLatter(
        index: Int,
        answerLetter: Letter,
    ) = !(wordResult.isCorrectLetterMatch(index) || isCorrectLetter(index, answerLetter)) && (answerLetter in markerLetters)

    private fun changeMarkerLetter(index: Int) {
        markerLetters[index] = markerLetters[index].changeMatchMarker()
    }

    private fun changeMarkerLetterIndexOf(letter: Letter) {
        changeMarkerLetter(markerLetters.indexOf(letter))
    }
}

fun WordComparator(todayWord: Word): WordComparator {
    return WordComparator(todayWord.map(Letter::copy).toMutableList())
}
