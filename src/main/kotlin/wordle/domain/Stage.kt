package wordle.domain

/**
 * 사용자가 하나의 정답을 가지고 진행하는 워들 한 판
 */
data class Stage(val answer: String, val steps: List<Step> = listOf()) {

    enum class State {
        PROGRESS, COMPLETE, FAIL
    }

    val state: State = when {
        steps.any { step -> step.isCorrect } -> State.COMPLETE
        steps.size == 6 -> State.FAIL
        else -> State.PROGRESS
    }

    val finished = state != State.PROGRESS

    fun play(word: Word): Stage {
        if (finished) return this
        val step = Step(answer, word)
        val newSteps = steps.toMutableList().apply {
            add(step)
        }

        return Stage(
            answer = answer,
            steps = newSteps,
        )
    }
}
