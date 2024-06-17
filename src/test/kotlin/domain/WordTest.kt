package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import wordle.domain.Word

class WordTest {
    @Test
    fun `(성공) 단어를 생성한다`() {
        assertThat(Word("hello")).isEqualTo(Word("hello"))
    }

    @EmptySource
    @ValueSource(strings = [" ", "     "])
    @ParameterizedTest
    fun `(예외) 단어는 공백만 입력할 수 없다`(invalidWord: String) {
        assertThatThrownBy { (Word(invalidWord)) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @ValueSource(strings = ["word", "aaaaaa"])
    @ParameterizedTest
    fun `(예외) 단어의 길이는 5자 이어야 한다`(invalidWord: String) {
        assertThatThrownBy { (Word(invalidWord)) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @ValueSource(strings = ["aaaaa", "abced"])
    @ParameterizedTest
    fun `(예외) 단어는 사전에 있는 단어이어야 한다`(invalidWord: String) {
        assertThatThrownBy { (Word(invalidWord)) }
            .isInstanceOf(IllegalStateException::class.java)
    }
}
