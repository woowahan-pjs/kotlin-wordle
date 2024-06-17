package wordle.controller

import wordle.domain.TodayWord
import wordle.domain.WordleGame
import wordle.view.printWelcome
import java.time.LocalDate

class WordleGameController {
    fun run() {
        printWelcome()
        WordleGame().play(TodayWord(LocalDate.now()))
    }
}
