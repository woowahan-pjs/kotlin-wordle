package stage.step.word


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WordTest {
  @ParameterizedTest
  @ValueSource(strings = ["-1", "1", " ", "w!", " ;fs"])
  fun `모두 영문으로 구성된다`(value: String) {
    //when
    val errorResponse = assertThrows<IllegalArgumentException> { Word.fromInput(value) }

    ///then
    assertThat(errorResponse.message).isEqualTo("영문만 입력해야합니다.")
  }

  @ParameterizedTest
  @ValueSource(strings = ["test", "hi", "h", "tttttt"])
  fun `5글자가 아니면 IllegalArgumentException 예외가 발생한다`(value: String) {
    //when
    val errorResponse = assertThrows<IllegalArgumentException> { Word.fromInput(value) }

    ///then
    assertThat(errorResponse.message).isEqualTo("5글자여야 합니다.")
  }

  @Test
  fun `영문 대문자는 소문자로 치환된다`() {
    assertThat(Word.fromInput("Hello")).isEqualTo(Word.fromInput("hello"))
  }

  @ParameterizedTest
  @ValueSource(strings = ["testa", "hiwww", "heeee", "tttaa"])
  fun `존재하지 않으면 IllegalArgumentException 예외가 발생한다`(value: String) {
    val errorResponse =
      assertThrows<IllegalArgumentException> { Word.fromInput(value) { _ -> false } }

    //then
    assertThat(errorResponse.message).isEqualTo("존재하지 않는 단어입니다.")
  }
}

data class Word(val value: String) {
  companion object {
    private val englishRegex = Regex("^[A-Za-z]*")
    private fun validateInput(input: String) {
      if (!input.matches(englishRegex)) {
        throw IllegalArgumentException("영문만 입력해야합니다.")
      }
      if (input.length != 5) {
        throw IllegalArgumentException("5글자여야 합니다.")
      }
    }

    private fun checkInDict(isWordInDic: Boolean) {
      if (!isWordInDic) {
        throw IllegalArgumentException("존재하지 않는 단어입니다.")
      }
    }

    fun fromInput(input: String, dicPolicy: (s: String) -> Boolean = { _ -> true }): Word {
      validateInput(input)
      val word = input.lowercase()
      checkInDict(dicPolicy(word))
      return Word(word)
    }
  }
}

