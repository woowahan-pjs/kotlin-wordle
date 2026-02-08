package wordle.service

import wordle.domain.AnswerGenerator
import wordle.domain.Results
import wordle.domain.Round
import wordle.domain.Word
import wordle.domain.WordBook
import wordle.domain.Wordle
import wordle.domain.WordleResults
import wordle.domain.WordleWordValidator
import wordle.io.ConsoleCommunicator
import wordle.io.ConsolePrinter
import wordle.io.ConsoleScanner
import wordle.io.Printer
import wordle.io.Scanner
import wordle.translation.WordBookExtractor
import java.time.LocalDate

/**
 * Wordle 게임을 관리합니다.
 */
class GameMachine(
    private val wordBook: WordBook = WordBookExtractor.extract(WORDS_FILE_NAME),
    private val answer: Word = AnswerGenerator(wordBook).generateAnswer(LocalDate.now()),
    private val printer: Printer = ConsolePrinter(),
    private val scanner: Scanner = ConsoleScanner(),
) {
    private val wordleWordValidator: WordleWordValidator = WordleWordValidator(wordBook)

    private val communicator: ConsoleCommunicator = ConsoleCommunicator(printer, scanner)

    /**
     * 게임을 시작합니다.
     *
     * @param count 게임 진행 카운트 수
     */
    fun start(count: Int) {
        var round: Round = Round()
        val wordleResults = WordleResults()

        // 소개 하기
        printer.introduce()

        while (!round.isGreaterThanRound(count)) {
            round = round.next()

            val wordle = Wordle(wordleWordValidator, answer)

            val results: Results
            try {
                val input: String = communicator.requestInput()
                results = wordle.round(input)
            } catch (e: Exception) {
                printer.error(e.message ?: "오류가 발생하였습니다.")
                continue
            }

            wordleResults.add(results)

            // 게임 머신이 게임 진행 여부 판단하기
            if (results.isAnswer()) {
                break
            }

            printer.viewTile(wordleResults.display())
        }

        // 최종 결과 출력하기
        printer.result(count, round, wordleResults.display())
    }

    companion object {
        const val WORDS_FILE_NAME = "words.txt"
    }
}
