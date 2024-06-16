package wordle

import wordle.domain.Stage
import wordle.domain.Word
import wordle.view.Input
import wordle.view.Output
import java.time.LocalDate

class Game(today: LocalDate) {
    private val answer = Dictionary.findTodayWord(today)
    private var stage = Stage(answer = answer)

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
                require(Dictionary.hasWord(word)) { "존재하지 않는 단어입니다." }
                return word
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}