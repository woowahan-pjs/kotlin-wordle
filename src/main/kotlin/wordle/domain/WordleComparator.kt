package wordle.domain

import wordle.domain.TileColor.*

class WordleComparator {

    fun getTileColors(secretWord: Word, guessedWord: Word): WordleGameResult {
        val result = MutableList(secretWord.value.length) { GRAY }

        val secretWordChars = secretWord.value.toCharArray()
        val guessedWordChars = guessedWord.value.toCharArray()

        replaceToGreen(secretWordChars, guessedWordChars, result)
        replaceToYellow(secretWordChars, guessedWordChars, result)

        return WordleGameResult(result)
    }

    private fun replaceToGreen(
        secretWordChars: CharArray,
        guessedWordChars: CharArray,
        result: MutableList<TileColor>
    ) {
        guessedWordChars.forEachIndexed { index, guessWord ->
            if (secretWordChars[index] == guessWord) {
                result[index] = GREEN
                secretWordChars[index] = EMPTY_REPLACEMENT
                guessedWordChars[index] = EMPTY_REPLACEMENT
            }
        }
    }

    private fun replaceToYellow(
        secretWordChars: CharArray,
        guessedWordChars: CharArray,
        result: MutableList<TileColor>
    ) {
        guessedWordChars.forEachIndexed { index, guessWord ->
            if (isUnchecked(guessWord) && guessWord in secretWordChars) {
                result[index] = YELLOW
                val indexOfWordInSecretWord = secretWordChars.indexOf(guessWord)
                secretWordChars[indexOfWordInSecretWord] = EMPTY_REPLACEMENT
            }
        }
    }

    private fun isUnchecked(word: Char): Boolean = word != EMPTY_REPLACEMENT

    companion object {
        private const val EMPTY_REPLACEMENT = ' '
    }
}
