package wordle.domain.word

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WordValidatorTest {
    private val extractor = WordExtractor(listOf("zin", "devlife"))
    private val validator = WordValidator(extractor)

    @Test
    @DisplayName("전달받은 문자열이 5글자가 아니면 오류가 발생한다")
    fun test01() {
        // arrange
        val word = Word("test")

        // act & assert
        assertThatThrownBy { validator.validate(word) }
            .isInstanceOf(WordValidationException::class.java)
            .hasMessage("입력값은 5글자여야 합니다.")
    }

    @Test
    @DisplayName("전달받은 문자열이 빈 문자열이면 오류가 발생한다")
    fun test02() {
        // arrange
        val word = Word("")

        // act & assert
        assertThatThrownBy { validator.validate(word) }
            .isInstanceOf(WordValidationException::class.java)
            .hasMessage("입력값은 5글자여야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["12345", "ㄱㄴㄷㄹㅁ", "☆☆☆☆☆"])
    @DisplayName("전달받은 문자열은 영어가 아니면 오류가 발생한다")
    fun test03(input: String) {
        // arrange
        val word = Word(input)

        // act & assert
        assertThatThrownBy { validator.validate(word) }
            .isInstanceOf(WordValidationException::class.java)
            .hasMessage("입력값은 영어여야 합니다.")
    }

    @Test
    @DisplayName("전달받은 문자열이 단어장에 없으면 에러를 발생한다")
    fun test04() {
        // arrange
        val word = Word("testt")

        // act & assert
        assertThatThrownBy { validator.validate(word) }
            .isInstanceOf(WordValidationException::class.java)
            .hasMessage("입력값은 단어장에 존재하는 단어여야합니다.")
    }
}
