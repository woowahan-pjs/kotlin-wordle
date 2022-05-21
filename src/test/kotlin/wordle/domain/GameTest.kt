package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GameTest {

    @Test
    fun `등록된 단어가 아닌 경우 예외가 발생한다`() {
        val game = Game(listOf(Word("apple"), Word("hello"), Word("spicy")))

        shouldThrow<IllegalArgumentException> { game.match(Word("spell")) }
            .shouldHaveMessage("등록된 단어가 아닙니다.")
    }

    @Test
    fun `게임 종료시 true를 반환한다`() {
        val game = Game(
            listOf(Word("apple"), Word("hello"), Word("spicy")),
            LocalDate.of(2021, 6, 19), 0
        )

        game.isGameOver(Word("hello")) shouldBe true
    }

    @Test
    fun `게임 종료시 false를 반환한다`() {
        val game = Game(
            listOf(Word("apple"), Word("hello"), Word("spicy")),
            LocalDate.of(2021, 6, 19), 1
        )

        game.isGameOver(Word("hello")) shouldBe false
    }
}
