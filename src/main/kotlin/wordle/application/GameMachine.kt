package wordle.application

import wordle.domain.result.Results
import wordle.domain.result.WordleResults
import wordle.domain.word.Answer
import wordle.domain.word.Word
import wordle.domain.word.WordExtractor
import wordle.domain.word.WordGenerator
import wordle.domain.word.WordValidationException
import wordle.domain.word.WordValidator
import wordle.infrastructure.FileReader
import wordle.infrastructure.Scanner
import wordle.view.Printer
import java.time.LocalDate

class GameMachine(
    fileName: String,
    private val count: Int,
) {
    private val extractor: WordExtractor = createExtractor(fileName)
    private val validator = WordValidator(extractor)

    fun start() {
        val answer = getAnswer()
        Printer.introduce()

        val (currentCount, wordleResults) = playAllRounds(answer)

        Printer.result(count, currentCount, wordleResults.display())
    }

    private fun playAllRounds(answer: Answer): Pair<Int, WordleResults> {
        var currentCount = 0
        val wordleResults = WordleResults()

        while (currentCount < count) {
            currentCount++

            val isCorrect = playRound(answer, wordleResults)
            if (isCorrect) break

            Printer.viewTile(wordleResults.display())
        }

        return Pair(currentCount, wordleResults)
    }

    private fun playRound(
        answer: Answer,
        wordleResults: WordleResults,
    ): Boolean {
        Printer.requestInput()
        val input = Word(Scanner.input())

        try {
            validator.validate(input)
        } catch (_: WordValidationException) {
            return false
        }

        val results: Results = Wordle(answer).round(input)
        wordleResults.add(results)

        return results.isAnswer()
    }

    private fun getAnswer(): Answer {
        val word = WordGenerator(extractor).generateAnswer(LocalDate.now())
        return Answer(word)
    }

    private fun createExtractor(fileName: String): WordExtractor {
        return WordExtractor(FileReader.read(fileName))
    }
}
