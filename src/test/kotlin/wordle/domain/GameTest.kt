package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class GameTest {

    @Test
    fun 게임을_한_라운드씩_진행() {
        val game = Game("fetus")
        repeat(3) { game.playRound(Answer("apple")) }

        assertAll(
            { assertThat(game.findTryCount()).isEqualTo(3) },
            { assertThat(game.isPlaying).isTrue() }
        )
    }

    @Test
    fun 게임을_한_라운드씩_진행하다_6라운드에_도달하면_게임종료() {
        val game = Game("fetus")
        repeat(6) { game.playRound(Answer("apple")) }

        assertAll(
            { assertThat(game.findTryCount()).isEqualTo(6) },
            { assertThat(game.isPlaying).isFalse() }
        )
    }

    @Test
    fun 게임을_한_라운드씩_진행하다_정답을_맞추면_게임종료() {
        val game = Game("fetus")
        repeat(3) { game.playRound(Answer("fetus")) }

        assertAll(
            { assertThat(game.findTryCount()).isEqualTo(3) },
            { assertThat(game.isPlaying).isFalse() }
        )
    }

    @Test
    fun 몇_번째_시도인지_계산() {
        val game = Game("fetus")
        repeat(6) { game.playRound(Answer("apple")) }

        assertThat(game.findTryCount()).isEqualTo(6)
    }
}
