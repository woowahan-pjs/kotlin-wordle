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

    @Test
    fun `정답과 답안을 비교하여 결과 타일을 담아 전달한다`() {
        val givenAnswer = Word("hello")
        val game = Game(givenAnswer)
        val input = Word("oxide")

        game.play(input)

        val resultTiles = game.retrieveResultTiles()

        assertThat(resultTiles.tiles).containsExactly(
            Tile.YELLOW, Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY
        )
    }
}
