package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `등록된 단어가 아닌 경우 예외가 발생한다`() {
        val game = Game(listOf(Word("apple"), Word("hello"), Word("spicy")))

        val exception = shouldThrow<IllegalArgumentException> {
            game.match(Word("spell"))
        }
        exception.message shouldBe "등록된 단어가 아닙니다."
    }

    @Test
    internal fun `게임 종료여부를 확인한다`() {
        val game = Game(listOf(Word("apple"), Word("hello"), Word("spicy")))
        repeat(6) { game.match(Word("apple")) }
        game.isGameOver(Word("apple")) shouldBe true
    }
}
