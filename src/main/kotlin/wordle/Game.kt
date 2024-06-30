package wordle

import wordle.domain.Stage
import wordle.domain.Word
import wordle.infra.FileDictionary
import wordle.view.Input
import wordle.view.Output
import java.time.LocalDate

class Game(today: LocalDate) {
    private val dictionary = FileDictionary()
    private val answerSelector = TodayAnswerSelector(today)
    private var stage = Stage(answer = dictionary.findAnswer(answerSelector))

    fun start() {
        Output.start()
        while (!stage.finished) {
            val word = readWord()
            stage = stage.play(word)
            Output.show(stage)
        }
    }

    private fun readWord(): Word {
        while (true) {
            try {
                val word = Input.guess()
                require(dictionary.hasWord(word)) { "존재하지 않는 단어입니다." }
                return word
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}