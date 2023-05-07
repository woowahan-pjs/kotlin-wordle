package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LetterTest {

    @ParameterizedTest
    @ValueSource(chars = ['가', 'A', '1'])
    fun `글자가 소문자 알파벳이 아니면 예외`(letter: Char) {
        //when & then
        assertThrows<IllegalArgumentException> { Letter(0, letter) }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 5])
    fun `글자 위치가 0 ~ 4가 아니면 예외`(position: Int) {
        //when & then
        assertThrows<IllegalArgumentException> { Letter(position, 'a') }
    }

    @ParameterizedTest
    @CsvSource(delimiter = ':', value = ["0:a", "4:z"])
    fun `글자가 소문자이면서 위치가 0 ~ 4이내면 예외가 발생하지 않는다`(position: Int, letter: Char) {
        //when & then
        assertDoesNotThrow { Letter(position, letter) }
    }

    @Test
    fun `글자 두 개를 비교해서 position과 글자가 일치하면 GREEN을 반환한다`() {
        val letter1 = Letter(0, 'a')
        val letter2 = Letter(0, 'a')

        assertThat(letter1.match(letter2)).isEqualTo(MatchResult.GREEN)
    }

    @Test
    fun `글자 두 개를 비교해서 글자만 일치하면 YELLOW을 반환한다`() {
        val letter1 = Letter(0, 'a')
        val letter2 = Letter(1, 'a')

        assertThat(letter1.match(letter2)).isEqualTo(MatchResult.YELLOW)
    }

    @Test
    fun `글자 두 개를 비교해서 position과 글자 아무것도 일치하지 않으면 GRAY을 반환한다`() {
        val letter1 = Letter(0, 'a')
        val letter2 = Letter(1, 'b')

        assertThat(letter1.match(letter2)).isEqualTo(MatchResult.GRAY)
    }
}
