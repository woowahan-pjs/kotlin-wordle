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

    private var trialCount = 0

    fun run() {
        OutputView.printGameStart()
        val answer = AnswerGenerator(WordPool.words).generate(LocalDate.now())
        val results: Results = Results()

        do {
            val word: Word = getValidWord()
            val result: Result = answer.match(word)
            results.add(result)
            trialCount++
            OutputView.printGameResults(results)
        } while (isContinue(result))

        OutputView.printTrialCount(trialCount, MAX_TRIAL_COUNT)
    }

    private fun isContinue(result: Result): Boolean {
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
        const val MAX_TRIAL_COUNT = 6
    }
}
