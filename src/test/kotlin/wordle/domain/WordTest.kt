package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

class WordTest {

    @Test
    fun `5글자가 아닌 word를 생성하면 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> { Word("test") }
            .shouldHaveMessage("단어의 길이는 5글자여야 합니다.")
    }

    @Test
    fun `영어가 아닌 글자가 포함된 word를 생성하면 예외가 발생한다`() {
        listOf("ap pl", "ap피le").forAll {
            shouldThrow<IllegalArgumentException> { Word(it) }
                .shouldHaveMessage("단어에 영어가 아닌 글자나 공백이 포함될 수 없습니다.")
        }
    }

    @Test
    fun `해당 글자가 단어의 특정 인덱스와 일치하는지 확인한다`() {
        val word = Word("style")
        val other = Word("soooo")
        word.isSameChar(other, 0) shouldBe true
        word.isSameChar(other, 1) shouldBe false
    }
}
