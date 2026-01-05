package stage

import stage.step.Step

data class Stage(val answer: String, var state: State = State.PROGRESS) {
  val STEP_SIZE = 6
  val steps = mutableListOf<Step>()

  enum class State {
    PROGRESS, COMPLETE, FAIL
  }

  fun play(word: String) {
    if (!canPlay()) return
    val step = Step.create(answer, word)
    steps.add(step)

    if (step.isCorrect) {
      state = State.COMPLETE
    }

    if (steps.size == STEP_SIZE && state != State.COMPLETE) {
      state = State.FAIL
    }
  }

  fun canPlay(): Boolean {
    return state === State.PROGRESS
  }
}
