package wordle.domain

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GameTest {

    @Test
    fun `정답을 6번 미만으로 입력한 경우 게임 진행 가능`() {
        val game = Game("fetus")
        repeat(5) { game.playRound(Answer("apple")) }

        assertSoftly(game) {
            findTryCount() shouldBeExactly 5
            isPlaying shouldBe true
        }
    }

    @Test
    fun `게임을 한 라운드씩 진행하다 6라운드에 도달하면 게임종료`() {
        val game = Game("fetus")
        repeat(6) { game.playRound(Answer("apple")) }

        assertSoftly(game) {
            findTryCount() shouldBeExactly 6
            isPlaying shouldBe false
        }
    }

    @Test
    fun `게임 6라운드 이전에 정답을 맞추면 게임종료`() {
        val game = Game("fetus")
        repeat(3) { game.playRound(Answer("fetus")) }

        assertSoftly(game) {
            findTryCount() shouldBeExactly 3
            isPlaying shouldBe false
        }
    }

    @Test
    fun `몇번째 시도인지 계산`() {
        val game = Game("fetus")
        repeat(6) { game.playRound(Answer("apple")) }

        game.findTryCount() shouldBeExactly 6
    }
}
