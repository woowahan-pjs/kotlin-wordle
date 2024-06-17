package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import wordle.domain.Letter

class LetterTest {
    @Test
    fun `(성공) 알파벳 소문자 글자를 생성한다`() {
        val actual = Letter('a')

        assertThat(actual).isEqualTo(Letter('a'))
    }

    @Test
    fun `(성공) 일치 표시 기호를 생성한다`() {
        val actual = Letter(MATCH_MARKER)

        assertThat(actual).isEqualTo(Letter(MATCH_MARKER))
    }

    @ValueSource(strings = [" ", "1", "A", "나", "@"])
    @ParameterizedTest
    fun `(예외) 유효하지 않은 글자를 생성하면 예외 발생한다`(letter: Char) {
        assertThatThrownBy { (Letter(letter)) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("유효하지 않은 글자 형식입니다.")
    }
}

private const val MATCH_MARKER = '#'
