package domain

import domain.MatchResult.INCORRECT
import domain.MatchResult.MISSING
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class GameTest {
    @Test
    fun `답안에 정답이 있을 경우 게임이 종료된다`() {
        // given
        val answer = listOf(Tile('h'), Tile('e'), Tile('l'), Tile('l'), Tile('o'))
        val input = TestInput(LinkedList(listOf("hello", "hello", "hello", "hello", "hello", "hello")))
        val output = TestOutput()
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(input, output, repository)

        // when
        game.start()

        // then
        assertThat(repository.calledTodayWords).isTrue
        assertThat(repository.tilesStack).contains(Tiles(answer))
        assertThat(input.result).hasSize(5)
        assertThat(output.resultsStack).hasSize(1)
        assertThat(output.resultsStack[0].isCorrect()).isTrue
    }

    @Test
    fun `답을 못맞출 경우 최대 6번을 입력할 기회가 주어진다`() {
        // given
        val answer = listOf(Tile('h'), Tile('e'), Tile('l'), Tile('l'), Tile('o'))
        val input = TestInput(LinkedList(listOf("abcde", "abcde", "abcde", "abcde", "abcde", "abcde")))
        val output = TestOutput()
        val repository = TestWordsRepository(answer, setOf(Tiles(answer), Tiles.of("abcde")))
        val game = Game(input, output, repository)

        // when
        game.start()

        // then
        assertThat(input.result).isEmpty()
        assertThat(repository.calledTodayWords).isTrue
        assertThat(output.resultsStack).hasSize(6)
        assertThat(output.resultsStack)
            .containsOnly(MatchResults(listOf(INCORRECT, INCORRECT, INCORRECT, INCORRECT, MISSING)))
    }

    @Test
    fun `게임은 7번 이상 입력시 6번만 입력 된다`() {
        // given
        val answer = listOf(Tile('h'), Tile('e'), Tile('l'), Tile('l'), Tile('o'))
        val input = TestInput(LinkedList(listOf("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde")))
        val output = TestOutput()
        val repository = TestWordsRepository(answer, setOf(Tiles(answer), Tiles.of("abcde")))
        val game = Game(input, output, repository)

        // when
        game.start()

        // then
        assertThat(input.result).hasSize(1)
        assertThat(output.resultsStack).hasSize(6)
        assertThat(output.resultsStack)
            .flatMap({it.isCorrect()})
            .containsExactly(false, false, false, false, false, false)
    }

    @Test
    fun `존재하지 않는 단어를 입력하면 재입력 기회를 준다`() {
        // given
        val answer = listOf(Tile('h'), Tile('e'), Tile('l'), Tile('l'), Tile('o'))
        val input = TestInput(LinkedList(listOf("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "hello")))
        val output = TestOutput()
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(input, output, repository)

        // when
        game.start()

        // then
        assertThat(input.result).hasSize(0)
        assertThat(output.resultsStack).hasSize(1)
    }
}

class TestInput(val result: Queue<String>) : Input {
    override fun read(): Tiles {
        return Tiles.of(result.poll())
    }
}

class TestOutput : Output {
    val resultsStack = mutableListOf<MatchResults>()

    override fun write(matchResults: MatchResults) {
        resultsStack.add(matchResults)
    }
}

class TestWordsRepository(val answer: List<Tile>, val words: Set<Tiles>) : WordsRepository {
    var tilesStack = mutableListOf<Tiles>()
    var calledTodayWords = false

    override fun exists(tiles: Tiles): Boolean {
        tilesStack.add(tiles)

        return words.contains(tiles)
    }

    override fun getTodayWords(): List<Tile> {
        calledTodayWords = true
        return answer
    }
}