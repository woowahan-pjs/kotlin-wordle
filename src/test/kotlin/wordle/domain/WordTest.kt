package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class WordTest {

    @Test
    fun `단어를 정상 생성해야 한다`() {
        val word = Word("model")

        word.value shouldBe "model"
    }

    @Test
    fun `단어가 5글자가 아니라면 예외를 던진다`() {
        shouldThrow<IllegalArgumentException> { Word("models") }
    }

    @Test
    fun `단어가 알파벳이 아니라면 예외를 던진다`() {
        shouldThrow<IllegalArgumentException> { Word("모델입니다") }
    }
}
