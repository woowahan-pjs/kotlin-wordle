package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class GameTest {

    @Test
    fun `게임을 한 라운드씩 진행`() {
        val game = Game("fetus")
        repeat(3) { game.playRound(Answer("apple")) }

        assertAll(
            {assertThat(game.findTryCount()).isEqualTo(3)},
            {assertThat(game.isPlaying).isTrue()}
        )
    }

    @Test
    fun `게임을 한 라운드씩 진행하다 6라운드에 도달하면 게임종료`() {
        val game = Game("fetus")
        repeat(6) { game.playRound(Answer("apple")) }

        assertAll(
            {assertThat(game.findTryCount()).isEqualTo(6)},
            {assertThat(game.isPlaying).isFalse()}
        )
    }

    @Test
    fun `게임을 한 라운드씩 진행하다 정답을 맞추면 게임종료`() {
        val game = Game("fetus")
        repeat(3) { game.playRound(Answer("fetus")) }

        assertAll(
            {assertThat(game.findTryCount()).isEqualTo(3)},
            {assertThat(game.isPlaying).isFalse()}
        )
    }

    @Test
    fun `몇번째 시도인지 계산`() {
        val game = Game("fetus")
        repeat(6) { game.playRound(Answer("apple")) }

        assertThat(game.findTryCount()).isEqualTo(6)
    }
}
