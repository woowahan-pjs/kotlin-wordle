import dictionary.Dictionary
import stage.Stage
import view.Input
import view.Print
import wordle.Word
import java.time.LocalDate

class Game {
  companion object {
    fun start(now: LocalDate) {
      val answer = Dictionary.findTodayWord(now)
      val stage = Stage(answer = answer)
      Print.start()
      while (stage.state == Stage.State.PROGRESS) {
        try {
          Print.requestInput()
          val inputValue = Input.write()
          val value = Word.fromInput(inputValue) { Dictionary.hasWord(inputValue) }.value
          stage.play(value)
          Print.resultStage(stage, answer)
        } catch (e: Exception) {
          println(e.message)
        }
      }
    }
  }
}