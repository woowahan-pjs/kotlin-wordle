package domain

import domain.PlayResult.FAILED
import domain.PlayResult.SUCCEEDED
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `답안에 정답이 있을 경우 게임이 종료된다`() {
        // given
        val answer = Tiles("hello")
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(repository)

        // when
        game.play(Tiles("hello"))

        // then
        assertThat(repository.calledTodayWords).isTrue
        assertThat(repository.tilesStack).contains(Tiles(answer))
        assertThat(game.isPlaying()).isFalse
    }

    @Test
    fun `답을 못맞출 경우 최대 6번을 입력할 기회가 주어진다`() {
        // given
        val answer = Tiles("hello")
        val repository = TestWordsRepository(answer, setOf(Tiles(answer), Tiles("abcde")))
        val game = Game(repository)

        // when
        val results = (1..6).map { game.play(Tiles("abcde")) }

        // then
        assertThat(results).containsOnly(SUCCEEDED)
        assertThat(repository.calledTodayWords).isTrue
        assertThat(game.isPlaying()).isFalse
    }

    @Test
    fun `게임은 7번 이상 입력시 6번만 입력 된다`() {
        // given
        val answer = Tiles("hello")
        val repository = TestWordsRepository(answer, setOf(Tiles(answer), Tiles("abcde")))
        val game = Game(repository)

        // when
        val results = (1..7).map { game.play(Tiles("abcde")) }

        // then
        assertThat(results).containsExactly(SUCCEEDED, SUCCEEDED, SUCCEEDED, SUCCEEDED, SUCCEEDED, SUCCEEDED, FAILED)
        assertThat(repository.calledTodayWords).isTrue
        assertThat(game.isPlaying()).isFalse
    }

    @Test
    fun `존재하지 않는 단어를 입력하면 재입력 기회를 준다`() {
        // given
        val answer = Tiles("hello")
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(repository)

        // when
        game.play(Tiles("nonon"))

        // then
        assertThat(game.allMatchResults).hasSize(0)
    }
}

class TestWordsRepository(val answer: Tiles, val words: Set<Tiles>) : WordsPool {
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
