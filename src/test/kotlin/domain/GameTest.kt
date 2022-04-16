package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import java.util.LinkedList
import java.util.Queue

class GameTest {
    @Test
    fun `답안에 정답이 있을 경우 게임이 종료된다`() {
        // given
        val answer = Tiles.of("hello")
        val input = TestInput(LinkedList(listOf("hello")))
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(repository)

        // when
        val result = game.progress(input)

        // then
        assertThat(result.isCorrect()).isTrue
    }

    @Test
    fun `답안을 7번 이상 적으면 예외를 발생시킨다`() {
        // given
        val answer = Tiles.of("hello")
        val input = TestInput(LinkedList(listOf("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde")))
        val repository = TestWordsRepository(answer, setOf(Tiles(answer), Tiles.of("abcde")))
        val game = Game(repository)

        // then
        assertThatIllegalArgumentException().isThrownBy {
            game.progress(input)
            game.progress(input)
            game.progress(input)
            game.progress(input)
            game.progress(input)
            game.progress(input)
            game.progress(input)
        }
    }

    @Test
    fun `존재하지 않는 단어를 입력하면 예외를 발생시킨다`() {
        // given
        val answer = Tiles.of("hello")
        val input =
            TestInput(LinkedList(listOf("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde")))
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(repository)

        // then
        assertThatIllegalArgumentException().isThrownBy {
            game.progress(input)
        }
    }
}

class TestInput(private val result: Queue<String>) : Input {
    override fun read(): Tiles {
        return Tiles.of(result.poll())
    }
}

class TestWordsRepository(val answer: Tiles, val words: Set<Tiles>) : Words {
    var tilesStack = mutableListOf<Tiles>()
    var calledTodayWords = false

    override fun exists(tiles: Tiles): Boolean {
        tilesStack.add(tiles)

        return words.contains(tiles)
    }

    override fun getTodayWords(): Tiles {
        calledTodayWords = true
        return answer
    }
}
