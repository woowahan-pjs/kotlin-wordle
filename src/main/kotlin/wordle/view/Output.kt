package wordle.view

import wordle.domain.Stage
import wordle.domain.Step

object Output {

    fun start() {
        println(
            """
                WORDLE을 6번 만에 맞춰 보세요.
                시도의 결과는 타일의 색 변화로 나타납니다.
            """.trimIndent()
        )
    }

    fun show(stage: Stage) {
        when (stage.state) {
            Stage.State.FAIL -> {
                println("X/6")
                showAllSteps(stage)
                println("answer = ${stage.answer}")
            }

            Stage.State.COMPLETE -> {
                println("${stage.steps.size}/6")
                showAllSteps(stage)
            }

            Stage.State.PROGRESS -> {
                showAllSteps(stage)
            }
        }
    }

    fun error(e: Exception) {
        println(e.message)
    }

    private fun showAllSteps(stage: Stage) {
        stage.steps.forEach { showStep(it) }
    }

    private fun showStep(step: Step) {
        println(step.result.joinToString("") {
            when (it) {
                Step.Result.CORRECT -> "\uD83D\uDFE9"
                Step.Result.MISMATCH -> "\uD83D\uDFE8"
                Step.Result.WRONG -> "⬜"
            }
        })
    }

}
