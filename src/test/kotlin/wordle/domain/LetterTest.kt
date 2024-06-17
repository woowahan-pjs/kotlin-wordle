package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

private const val MATCH_MARKER = '#'

class LetterTest {
    @Test
    fun `(성공) 알파벳 소문자 글자를 생성한다`() {
        val actual = Letter('a')

        assertThat(actual).isEqualTo(Letter('a'))
    }

    @Test
    fun `(성공) 체크 표시 글자를 생성한다`() {
        val actual = Letter(MATCH_MARKER)

        assertThat(actual).isEqualTo(Letter(MATCH_MARKER))
    }

    @ValueSource(strings = [" ", "1", "A", "@"])
    @ParameterizedTest
    fun `(예외) 유효하지 않은 글자를 생성하면 예외 발생한다`(letter: Char) {
        assertThatThrownBy { (Letter(letter)) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("유효하지 않은 글자입니다.")
    }

    @Test
    fun `(성공) 체크 표시 글자로 변경한다`() {
        val letter = Letter('b')

        val actual = letter.changeMatchMarker()

        assertThat(actual).isEqualTo(Letter(MATCH_MARKER))
    }
}
