package wordle

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WordTest {

    @Test
    fun `5글자가 아닌 word를 생성하면 예외가 발생한다`() {
        val exception = shouldThrow<IllegalArgumentException> {
            Word("test")
        }
        exception.message shouldBe "단어의 길이는 5글자여야 합니다."
    }

    @ParameterizedTest
    @ValueSource(strings = ["ap pl", "ap피le"])
    fun `영어가 아닌 글자가 포함된 word를 생성하면 예외가 발생한다`(word: String) {
        val exception = shouldThrow<IllegalArgumentException> {
            Word(word)
        }
        exception.message shouldBe "단어에 영어가 아닌 글자나 공백이 포함될 수 없습니다."
    }
}
