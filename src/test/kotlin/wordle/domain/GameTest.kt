package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `6번의 기회안에 5글자를 모두 맞추면 성공한다`() {
        val givenAnswer = Word("words")
        val game = Game(givenAnswer)
        val givenInput = Word("words")

        val result = game.play(givenInput)
        val isWinner = game.isWinner(result)

        assertThat(isWinner).isTrue
    }
}
