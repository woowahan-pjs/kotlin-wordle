package stage.step

data class Step(val code: List<Result>) {

  val isCorrect = code.all { it == Result.CORRECT }

  enum class Result(
    val color: String
  ) {
    CORRECT("\uD83D\uDFE9"),
    MISMATCH("\uD83D\uDFE8"),
    WRONG("⬜");
  }

  companion object {
    fun create(answer: String, word: String): Step {
      if (answer == word) {
        return Step(Array(5) { Result.CORRECT }.toList())
      }
      val answerBuilder = StringBuilder(answer)

      val correctResult = correct(answerBuilder = answerBuilder, word = word)
      mismatch(answerBuilder = answerBuilder, word = word, codes = correctResult)
      return Step(correctResult.toList())
    }

    fun correct(answerBuilder: StringBuilder, word: String): Array<Result> {
      val codes = Array(5) { Result.WRONG }

      word.filterIndexed { index, c -> answerBuilder[index] == c }.forEachIndexed { index, c ->
        codes[index] = Result.CORRECT
      }

      (4 downTo 0).forEach {
        if (codes[it] == Result.CORRECT) {
          answerBuilder.deleteAt(it)
        }
      }
      return codes
    }

    fun mismatch(
      answerBuilder: StringBuilder,
      word: String,
      codes: Array<Result>
    ): Array<Result> {

      word.forEachIndexed { index, c ->
        if (codes[index] != Result.CORRECT && answerBuilder.contains(c)) {
          codes[index] = Result.MISMATCH
          val indexOf = answerBuilder.indexOf(c)
          answerBuilder.deleteAt(indexOf)
        }
      }
      return codes
    }
  }
}