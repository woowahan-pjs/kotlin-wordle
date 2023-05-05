package wordle.domain

import wordle.domain.ResultType.*

class WordleCalculator {

    fun calculate(secretWord: String, guessedWord: String): List<ResultType> {
        val result = MutableList<ResultType>(secretWord.length) { GRAY }

        val secretWordChars = secretWord.toCharArray()
        val guessedWordChars = guessedWord.toCharArray()

        replaceToGreen(secretWordChars, guessedWordChars, result)
        replaceToYellow(secretWordChars, guessedWordChars, result)

        return result
    }

    private fun replaceToGreen(
        secretWordChars: CharArray,
        guessedWordChars: CharArray,
        result: MutableList<ResultType>
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
        result: MutableList<ResultType>
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
