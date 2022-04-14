package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `6번의 기회안에 5글자를 모두 맞추면 성공한다`() {
        val givenAnswer = Word("words")
        val game = Game(TryCount(2), givenAnswer)
        val givenInput = Word("words")

        val isWinner = game.play(givenInput)

        assertThat(isWinner).isTrue
    }

    @Test
    fun `5글자의 모든 위치와 스펠이 같지 않은 경우 시도 횟수가 더해진다`() {
        val givenAnswer = Word("hello")
        val game = Game(TryCount(3), givenAnswer)
        val givenInput = Word("input")

        game.play(givenInput)

        assertThat(game.tryCount()).isEqualTo(TryCount(4))
    }

//    @Test
//    fun `정답과 답안을 비교하여 결과 타일을 담아 전달한다`() {
//        val game = Game(TryCount(0))
//        val input = Word("input")
//        val answer = Word("hello")
//
//        game.play(input, answer)
//
//        val game = Game(TryCount(3))
//
//        List<Tile> resultTiles = game.retreiveResultTiles()
//
//        assertThat(resultTiles).isEqualTo(TryCount(4))
//    }
}
