package domain

import domain.vo.GameResult
import view.InputView
import view.ResultView

class RetryableGame(val wordleGame: WordleGame, val resultView: ResultView, val inputView: InputView) {
    fun run(answerWord: GameString) {
        inputView.printIntro()
        val accumulatedResults = mutableListOf<GameResult>()
        var currentCount = 0

        while (currentCount < Constant.MAX_GAME_TRY_COUNT) {
            inputView.printInputPrompt()
            val guessWord = inputView.getInput()
            val gameResult = wordleGame.logic(guessWord, answerWord)
            accumulatedResults.add(gameResult)
            currentCount++

            if (gameResult.isSuccess) {
                printSuccessResult(currentCount, accumulatedResults)
                return
            }
            resultView.printAccumulatedResults(accumulatedResults)
        }
    }

    fun printSuccessResult(
        attemptCount: Int,
        results: List<GameResult>,
    ) {
        resultView.printFinalScore(attemptCount)
        resultView.printAccumulatedResults(results)
    }
}
