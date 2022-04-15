package edu.nextstep.wordle.application.wordle

import edu.nextstep.wordle.application.wordle.dictionary.WordFinder

data class Wordle(
    val target: Word,
    val wordResult: List<WordResult> = emptyList(),
    val wordFinder: WordFinder,
) {
    val round = wordResult.size + 1

    fun input(word: Word): WordleAnswer {
        if (wordFinder.notContain(word)) {
            return WordleAnswer.Retry(this, "사전에 없는 단어(${word.rawWord()})입니다.")
        }

        val result = this.target.match(word)
        val wordResult = this.wordResult + WordResult(round = this.round, windowResults = result)

        return WordleAnswer.Right(this.copy(wordResult = wordResult))
    }

    private fun WordFinder.notContain(word: Word): Boolean = !this.contain(word)

    private fun Word.rawWord(): String {
        return windows.sortedBy { it.position }.joinToString(separator = "") { it.alphabet._value }
    }

    fun isSuccess(): Boolean {
        return WordResults(this.wordResult)
            .isSuccess()
    }

    fun isEnd(): Boolean {
        return round > MAX_ROUND || isSuccess()
    }

    companion object {
        private const val MAX_ROUND: Int = 6
    }
}
