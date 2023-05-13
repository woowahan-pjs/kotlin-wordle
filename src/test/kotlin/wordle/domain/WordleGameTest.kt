package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordleGameTest {

    @Test
    fun `정답을 맞춘 경우 게임이 종료된다`() {
        val game = WordleGame(Word("apple"))

        game.play(Word("apple"))

        assertThat(game.isEnd()).isTrue
    }

    @Test
    fun `맞추기를 6번 시도한 경우 게임이 종료된다`() {
        val game = WordleGame(Word("apple"))

        repeat(6) {
            game.play(Word("abcde"))
        }

        assertThat(game.isEnd()).isTrue
    }

    @Test
    fun `맞추기를 4번 시도하고 정답을 맞추지 못한 경우 게임이 종료되지 않는다`() {
        val game = WordleGame(Word("apple"))

        repeat(4) {
            game.play(Word("abcde"))
        }

        assertThat(game.isEnd()).isFalse
    }

    @Test
    fun `play를 통해 게임 결과가 반환된다`() {
        val game = WordleGame(Word("pplab"))

        val actual = game.play(Word("apppc"))

        assertThat(actual)
            .flatExtracting("result")
            .containsExactly(
                TileColor.YELLOW,
                TileColor.GREEN,
                TileColor.YELLOW,
                TileColor.GRAY,
                TileColor.GRAY
            )
    }
}
