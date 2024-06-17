package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WordTest {

    @Test
    fun `단어를 만들 수 있다`() {
        val givenWord = "value"
        val word = Word(givenWord)

        assertThat(word.value).isEqualTo(givenWord)
    }

    @ParameterizedTest
    @ValueSource(strings = ["abc", "abcdef"])
    fun `단어는 5글자가 아닌 경우 예외를 던진다`(wrongWord: String) {
        val message = assertThrows<IllegalArgumentException> {
            Word(wrongWord)
        }.message

        assertThat(message).isEqualTo("5글자여야합니다")
    }

    @Test
    fun `단어는 알파벳이어야한다`() {
        val givenWrongWord = "a1234"
        val message = assertThrows<IllegalArgumentException> {
            Word(givenWrongWord)
        }.message

        assertThat(message).isEqualTo("5글자여야합니다")
    }

    @Test
    fun `인덱스 위치에 있는 알파벳을 가져온다`() {
        val givenWord = "value"
        val word = Word(givenWord)

        val actual = word.findAlphabet(1)

        assertThat(actual).isEqualTo("a")
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 8])
    fun `인덱스 위치가 올바르지 않은 경우 예외를 던진다`(index: Int) {
        val givenWord = "value"
        val word = Word(givenWord)

        val message = assertThrows<IllegalArgumentException> {
            word.findAlphabet(index)
        }.message

        assertThat(message).isEqualTo("인덱스 범위를 초과했습니다.")
    }

    @Test
    fun `단어에 알파벳이 포함되면 참을 리턴한다`() {
        val givenWord = "value"
        val word = Word(givenWord)
        val givenAlphabet = "e"

        val actual = word.contains(givenAlphabet)

        assertThat(actual).isTrue
    }
}
