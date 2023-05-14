package wordle.controller

import wordle.domain.AnswerGenerator
import wordle.domain.Result
import wordle.domain.Results
import wordle.domain.Word
import wordle.domain.WordPool
import wordle.view.InputView
import wordle.view.OutputView
import java.time.LocalDate

class WordleGameController() {

    fun run() {
        var trialCount: Int = 0
        OutputView.printGameStart()
        val answer = AnswerGenerator(WordPool.words).generate(LocalDate.now())
        val results: Results = Results()

        do {
            val result: Result = matchWord(answer, results)
            trialCount++
        } while (isContinue(result, trialCount))

        OutputView.printTrialCount(trialCount, MAX_TRIAL_COUNT)
    }

    private fun matchWord(answer: Word, results: Results): Result {
        val word: Word = getValidWord()
        val result: Result = answer.match(word)
        results.add(result)
        OutputView.printGameResults(results)
        return result
    }

    private fun isContinue(result: Result, trialCount: Int): Boolean {
        return trialCount < MAX_TRIAL_COUNT && !result.isRight()
    }

    private fun getValidWord(): Word {
        return runCatching {
            Word.from(InputView.readWord())
        }.onFailure {
            OutputView.printError(it.message!!)
        }.getOrNull() ?: getValidWord()
    }

    companion object {
        private const val MAX_TRIAL_COUNT = 6
    }
}
