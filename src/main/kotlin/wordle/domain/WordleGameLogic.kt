package wordle.domain

class WordleGameLogic(private val todayWord: Word) {
    val tempList = todayWord.word.toMutableList()
    val wordResult = WordResult()

    fun compare(answerWord: Word): WordResult =
        answerWord.matchCorrect()
            .matchPresent()
            .toWordResult()

    private fun Word.matchCorrect(): Word {
        return onEachIndexed { index, letter ->
            if (isSame(index, letter)) {
                wordResult.changeMatchType(index, LetterMatch.CORRECT)
                tempList[index] = tempList[index].changeMatchMarker()
            }
        }
    }

    private fun Word.matchPresent(): Word {
        return onEachIndexed { index, letter ->
            if (!isSame(index, letter) && letter in tempList) {
                wordResult.changeMatchType(index, LetterMatch.PRESENT)
                tempList[tempList.indexOf(letter)] = tempList[tempList.indexOf(letter)].changeMatchMarker()
            }
        }
    }

    private fun Word.toWordResult() = wordResult

    private fun isSame(
        index: Int,
        letter: Letter,
    ) = todayWord.word[index] == letter
}
