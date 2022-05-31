package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import java.time.LocalDate

class GameTest : AnnotationSpec() {

    @Test
    fun `등록된 단어가 아닌 경우 예외가 발생한다`() {
        val game = Game(listOf(Word("apple"), Word("hello"), Word("spicy")))

        shouldThrow<IllegalArgumentException> { game.match(Word("spell")) }
            .shouldHaveMessage("등록된 단어가 아닙니다.")
    }

    @Test
    fun `시도 횟수를 채워 게임이 종료된 경우 true를 반환한다`() {
        val game = Game(
            listOf(Word("apple"), Word("hello"), Word("spicy")),
            LocalDate.of(2021, 6, 19), 0
        )

        game.isGameOver shouldBe true
    }

    @Test
    fun `정답을 맞춰 게임이 종료된 경우 true를 반환한다`() {
        val game = Game(
            listOf(Word("apple"), Word("hello"), Word("spicy")),
            LocalDate.of(2021, 6, 19)
        )

        game.match(Word("apple"))

        game.isGameOver shouldBe true
    }

    @Test
    fun `시도 횟수가 남은 경우 false를 반환한다`() {
        val game = Game(
            listOf(Word("apple"), Word("hello"), Word("spicy")),
            LocalDate.of(2021, 6, 19), 1
        )

        game.isGameOver shouldBe false
    }
}
