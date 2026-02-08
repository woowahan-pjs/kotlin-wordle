package wordle.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WordleWordValidatorTest {
    private val extractor: WordBook = WordBook.from(AnswerWordValidator(), listOf("zinzo", "devli"))
    private val validator = WordleWordValidator(extractor)

    @Test
    @DisplayName("전달받은 문자열이 5글자가 아니면 예외가 발생합니다.")
    fun test01() {
        // arrange
        val word = "test"

        // act & assert
        assertThatThrownBy { validator.validate(word) }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(strings = ["12345", "ㄱㄴㄷㄹㅁ", "☆☆☆☆☆"])
    @DisplayName("전달받은 문자열은 영어가 아니면 예외가 발생합니다.")
    fun test02(input: String) {
        // arrange
        val word = input

        // act & assert
        assertThatThrownBy { validator.validate(word) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력값은 영어여야 합니다.")
    }

    @Test
    @DisplayName("전달받은 문자열이 단어장에 없으면 예외가 발생합니다.")
    fun test03() {
        // arrange
        val word = "testt"

        // act & assert
        assertThatThrownBy { validator.validate(word) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("입력값은 단어장에 존재하는 단어여야합니다.")
    }
}
