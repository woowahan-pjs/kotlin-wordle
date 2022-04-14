package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `6번의 기회안에 5글자를 모두 맞추면 성공한다`() {
        val game = Game(TryCount(2))
        val input = Word("words")
        val answer = Word("words")

        val isWinner = game.play(input, answer)

        assertThat(isWinner).isTrue
    }

    @Test
    fun `5글자의 모든 위치와 스펠이 같지 않은 경우 시도 횟수가 더해진다`() {
        val game = Game(TryCount(3))
        val input = Word("input")
        val answer = Word("hello")

        game.play(input, answer)

        assertThat(game.tryCount()).isEqualTo(TryCount(4))
    }
}
