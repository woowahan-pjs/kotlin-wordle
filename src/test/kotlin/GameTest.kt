import stage.Stage
import stage.step.Step
import wordle.Word
import java.time.LocalDate
import java.util.Scanner


class GameTest {

  /**
   * todo
   * 테스트를 해야할까?
   * */

}


/**
 * 게임 시작시
 * - 시작문구 출력
 * - 정답만들기
 * 게임 진행시
 *  - 문구 입력받기
 *  - 결과 출력
 *  - 스테이지 끝나면(COMPLETE/FAIL) COMPLETE: 몇 스텝만에 맞췄는지 / FAIL: 정답이 뭔지
 *
 * 1. Dictionary 날짜 계산
 * 2.
 * */
class Game {

  companion object {
    fun start(now: LocalDate) {
      val answer = Dictionary.findTodayWord(now)
      val stage = Stage(answer = answer)
      Print.start()
      while (stage.state == Stage.State.PROGRESS) {
        try {
          val scanner = Scanner(System.`in`) // input 역할 나누기
          Print.requestInput()
          val inputValue = scanner.nextLine()
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

class Print {

  companion object {
    fun start() {
      println(
        """
                WORDLE을 6번 만에 맞춰 보세요.
                시도의 결과는 타일의 색 변화로 나타납니다.
            """.trimIndent()
      )
    }

    fun requestInput() {
      println("정답을 입력해 주세요.")
    }


    fun resultStage(stage: Stage, answer: String) {
      when (stage.state) {
        Stage.State.FAIL -> {
          resultAllStep(stage)
          println("answer = $answer")
        }

        Stage.State.COMPLETE -> {
          println("${stage.steps.size}/6")
          resultAllStep(stage)
        }

        Stage.State.PROGRESS -> {
          resultAllStep(stage)
        }
      }
    }

    fun resultAllStep(stage: Stage) {
      stage.steps.forEach { resultStep(it) }
    }

    fun resultStep(step: Step) {
      val str: StringBuilder = StringBuilder()
      step.code.forEach {
        when (it) {
          Step.Result.CORRECT -> str.append("\uD83D\uDFE9")
          Step.Result.MISMATCH -> str.append("\uD83D\uDFE8")
          Step.Result.WRONG -> str.append("⬜")
        }
      }
      println(str)
    }
  }
}

fun main(args: Array<String>) {
  Game.start(LocalDate.now())
}