package stage.step

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * 남은 기능
 * - 결과 값 담기 - Stage
 * - 출력하기 (UI)
 *
 * */


class StepTest {
  @Nested
  inner class ResultTest {
    @Test
    fun `완전히 동일한 단어일 경우 전부 CORRECT`() {
      val answer = "hello"
      val word = "hello"

      assertThat(
        Step.create(
          answer,
          word
        ).code
      ).isEqualTo(Array(5) { Step.Result.CORRECT }.toList())
    }

    @Test
    fun `단어는 포함되어 있고 위치가 일치하면 CORRECT`() {
      val answer = "hello"
      val word = "helol"

      val resultCode = Step.create(answer, word).code

      // then
      assertThat(resultCode[0]).isEqualTo(Step.Result.CORRECT)
      assertThat(resultCode[2]).isEqualTo(Step.Result.CORRECT)
    }

    @Test
    fun `단어는 포함되어 있으나 위치가 일치하지 않을경우 MISMATCH`() {
      val answer = "hello"
      val word = "helol"

      val resultCode = Step.create(answer, word).code

      // then
      assertThat(resultCode[3]).isEqualTo(Step.Result.MISMATCH)
      assertThat(resultCode[4]).isEqualTo(Step.Result.MISMATCH)
    }

    @Test
    fun `단어는 포함되어 있으나 위치가 일치하지 않고 정답개수의 단어보다 입력 갯수가 더 많을 경우 일치한 횟수(왼쪽에서 오른쪽으로 계산)가 적으면 MISMATCH`() {
      val answer = "hello"
      val word = "heool"

      val resultCode = Step.create(answer, word).code

      // then
      assertThat(resultCode[2]).isEqualTo(Step.Result.MISMATCH)
    }

    @Test
    fun `단어는 포함되어 있으나 위치가 일치하지 않고 정답개수의 단어보다 입력 갯수가 더 많을 경우 일치한 횟수(왼쪽에서 오른쪽으로 계산)가 많으면 WRONG`() {
      val answer = "hello"
      val word = "heool"

      val resultCode = Step.create(answer, word).code

      // then
      assertThat(resultCode[3]).isEqualTo(Step.Result.WRONG)
    }

    @Test
    fun `단어는 포함조차 안되어있으면 WRONG`() {
      val answer = "hello"
      val word = "helok"

      val resultCode = Step.create(answer, word).code

      // then
      assertThat(resultCode[4]).isEqualTo(Step.Result.WRONG)
    }
  }

  @Test
  fun `Result가 전부 CORRECT면 맞춤(isCorrect가 true)`() {
    assertThat(Step(listOf(Step.Result.CORRECT, Step.Result.CORRECT)).isCorrect).isTrue()
  }

  @Test
  fun `Result가 전부 CORRECT가 아닐 경우에는 틀림(isCorrect가 false)`() {
    assertThat(
      Step(
        listOf(
          Step.Result.CORRECT,
          Step.Result.MISMATCH,
          Step.Result.WRONG
        )
      ).isCorrect
    ).isFalse()
  }

}

data class Step(val code: List<Result>) {

  val isCorrect = code.all { it == Result.CORRECT }

  enum class Result {
    CORRECT,
    MISMATCH,
    WRONG;
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

      word.forEachIndexed { index, c ->
        if (answerBuilder[index] == c) {
          codes[index] = Result.CORRECT
        }
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
