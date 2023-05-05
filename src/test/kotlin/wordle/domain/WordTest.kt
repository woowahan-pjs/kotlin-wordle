package wordle.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WordTest {

    @Test
    fun `Word는 5글자가 아니면 예외를 반환한다`() {
        assertThrows<IllegalArgumentException> { Word("abcd") }
            .message shouldBe "단어는 5글자만 됩니다"
    }
}
