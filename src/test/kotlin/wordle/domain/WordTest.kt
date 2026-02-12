package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class WordTest {

    @Test
    @DisplayName("단어를 정상 생성해야 한다.")
    fun createWord() {
        val word = Word("model")
        assertThat(word.value).isEqualTo("model")
    }

    @Test
    @DisplayName("단어가 5글자가 아니라면 예외를 던진다.")
    fun validateSize() {
        shouldThrow<IllegalArgumentException> { Word("models") }
    }

    @Test
    @DisplayName("단어가 알파벳이 아니라면 예외를 던진다.")
    fun validateAlphabet() {
        shouldThrow<IllegalArgumentException> { Word("모델입니다") }
    }
}
