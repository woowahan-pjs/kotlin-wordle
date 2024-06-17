package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import wordle.exception.ExceptionMessage

class WordTest {
    @Test
    fun `단어를 생성한다`() {
        assertAll(
            { assertDoesNotThrow { Word("hello") } },
            { assertThat(Word("hello")).isEqualTo(Word("hello")) },
        )
    }

    @EmptySource
    @ValueSource(strings = [" ", "     "])
    @ParameterizedTest
    fun `공백을 허용하지 않는다`(word: String) {
        assertThatThrownBy { (Word(word)) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage(ExceptionMessage.WORD_NOT_BLANK.message)
    }

    @ValueSource(strings = ["word", "aaaaaa"])
    @ParameterizedTest
    fun `단어는 5자리이다`(word: String) {
        assertThatThrownBy { (Word(word)) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage(ExceptionMessage.INVALID_WORD_LENGTH.message)
    }

    @ValueSource(strings = ["aaaaa", "abced"])
    @ParameterizedTest
    fun `단어사전에 있는 단어이어야 한다`(word: String) {
        assertThatThrownBy { (Word(word)) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage(ExceptionMessage.WORD_NOT_FOUND.message)
    }
}
