package wordle.domain.word

import wordle.domain.result.Result
import wordle.domain.result.Results

class Answer(
    private val word: Word,
) {
    fun verify(guess: Word): Results {
        val result = initResult(word.length())
        val answer = word.toCharArray()
        val guessChars = guess.toCharArray()
        val counter = initCounter(word)
        applyCorrectMatches(answer, guessChars, result, counter)
        applyPresentMatches(answer, guessChars, result, counter)
        return Results(result)
    }

    private fun initResult(length: Int): MutableList<Result> {
        return MutableList(length) { Result.ABSENT }
    }

    private fun initCounter(word: Word): MutableMap<Char, Int> {
        return word.value.groupingBy { it }.eachCount().toMutableMap()
    }

    private fun applyCorrectMatches(
        answer: CharArray,
        guess: CharArray,
        result: MutableList<Result>,
        counter: MutableMap<Char, Int>,
    ) {
        for (i in guess.indices) {
            if (!word.matchesAt(i, guess[i])) continue
            result[i] = Result.CORRECT
            answer[i] = '_'
            decrementCounter(guess[i], counter)
        }
    }

    private fun applyPresentMatches(
        answer: CharArray,
        guess: CharArray,
        result: MutableList<Result>,
        counter: MutableMap<Char, Int>,
    ) {
        for (i in guess.indices) {
            if (answer[i] == '_') continue
            if ((counter[guess[i]] ?: 0) <= 0) continue
            decrementCounter(guess[i], counter)
            result[i] = Result.PRESENT
        }
    }

    private fun decrementCounter(
        char: Char,
        counter: MutableMap<Char, Int>,
    ) {
        val current = counter[char] ?: return
        counter[char] = current - 1
    }
}
