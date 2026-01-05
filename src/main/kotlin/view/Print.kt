package view

import stage.Stage
import stage.step.Step

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
          Step.Result.CORRECT -> Step.Result.CORRECT.color
          Step.Result.MISMATCH -> Step.Result.MISMATCH.color
          Step.Result.WRONG -> Step.Result.WRONG.color
        }
      }
      println(str)
    }
  }
}