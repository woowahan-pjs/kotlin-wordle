package wordle.domain

class WordComparator(
    private val letters: MutableList<Letter>,
    private val wordResult: WordResult = WordResult(),
) {
    fun matchCorrect(answerWord: Word): WordComparator =
        apply {
            letters.forEachIndexed { index, _ ->
                changeCorrectMatch(index, answerWord[index])
            }
        }

    fun matchPresent(answerWord: Word): WordComparator =
        apply {
            letters.forEachIndexed { index, _ ->
                changePresentMatch(index, answerWord[index])
            }
        }

    fun result(): WordResult = wordResult

    private fun changeCorrectMatch(
        index: Int,
        letter: Letter,
    ) {
        if (isCorrectLetter(index, letter)) {
            wordResult.changeMatchType(index, LetterMatch.CORRECT)
            changeLetter(index)
        }
    }

    private fun changePresentMatch(
        index: Int,
        letter: Letter,
    ) {
        if (isPresentLatter(index, letter)) {
            wordResult.changeMatchType(index, LetterMatch.PRESENT)
            changeLetters(letter)
        }
    }

    private fun isCorrectLetter(
        index: Int,
        letter: Letter,
    ) = letters[index] == letter

    private fun isPresentLatter(
        index: Int,
        letter: Letter,
    ) = !isCorrectLetter(index, letter) && contains(letter)

    private fun changeLetter(index: Int) {
        letters[index] = letters[index].changeMatchMarker()
    }

    private fun changeLetters(letter: Letter) {
        changeLetter(letters.indexOf(letter))
    }

    private fun contains(letter: Letter) = letter in letters
}

fun WordComparator(todayWord: Word): WordComparator {
    return WordComparator(todayWord.map(Letter::copy).toMutableList())
}
