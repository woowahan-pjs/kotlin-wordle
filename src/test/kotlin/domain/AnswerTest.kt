package domain

import domain.MatchResult.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class AnswerTest {
    @Test
    fun `Answer는 5개의 Tile로 구성이 되어있다`() {
        // given
        val elements = listOf(Tile('a'), Tile('b'), Tile('c'), Tile('d'), Tile('e'))

        // when
        val answer = Answer(elements)

        // then
        assertThat(answer.tiles).containsExactlyElementsOf(elements)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 6, 10])
    fun `Answer는 5개의 Tile로 구성이 안되면 실패한다`(size: Int) {
        // given
        val elements: List<Tile> = (0 until size).map { Tile('a') }

        // then
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { Answer(elements) }
            .withMessage(Answer.ERROR_TILE_SIZE_MSG)
    }

    @Test
    fun `Answer는 문자열 5글자로 구성 할 수 있다`() {
        // given
        val answer = Answer.of("hello")

        // then
        assertThat(answer.tiles)
            .containsExactly(Tile('h'), Tile('e'), Tile('l'), Tile('l'), Tile('o'))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "h", "hell", "hellow"])
    fun `Answer는 문자열 5글자로 구성이 안되면 실패한다`(words: String) {
        // then
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy { Answer.of(words) }
            .withMessage(Answer.ERROR_TILE_SIZE_MSG)
    }

    @Test
    fun `정답과 비교하여 같은 위치에 있으면 CORRECT이다`() {
        // given
        val answer = Answer.of("hello")
        val tiles = Tiles.of("hello")

        // when
        val matches: List<MatchResult> = answer.match(tiles)

        // then
        assertThat(matches).containsOnly(CORRECT)
    }

    @Test
    fun `정답은 아니지만 Tile이 있으면 MISSING이다`() {
        // given
        val answer = Answer.of("hello")
        val tiles = Tiles.of("olehl")

        // when
        val matches: List<MatchResult> = answer.match(tiles)

        // then
        assertThat(matches).containsOnly(MISSING)
    }

    @Test
    fun `정답에 Tile이 없으면 INCORRECT이다`() {
        // given
        val answer = Answer.of("hello")
        val tiles = Tiles.of("zzzzz")

        // when
        val matches: List<MatchResult> = answer.match(tiles)

        // then
        assertThat(matches).containsOnly(INCORRECT)
    }

    @Test
    fun `정답을 머저 CORRECT으로 변경하고 나머지 존재하는 부분을 MISSING으로 변경한다`() {
        // given
        val answer = Answer.of("hello")
        val tiles = Tiles.of("olleh")

        // when
        val matches: List<MatchResult> = answer.match(tiles)

        // then
        assertThat(matches).containsExactly(MISSING, MISSING, CORRECT, MISSING, MISSING)
    }

    @Test
    fun `정답이 뒤에 있으면 정답부터 CORRECT으로 변경된다`() {
        // given
        val answer = Answer.of("hello")
        val tiles = Tiles.of("lllll")

        // when
        val matches: List<MatchResult> = answer.match(tiles)

        // then
        assertThat(matches).containsExactly(INCORRECT, INCORRECT, CORRECT, CORRECT, INCORRECT)
    }

    @Test
    fun `답에 있는 타일 개수보다 많으면 INCORRECT으로 변경된다`() {
        // given
        val answer = Answer.of("hello")
        val tiles = Tiles.of("llool")

        // when
        val matches: List<MatchResult> = answer.match(tiles)

        // then
        assertThat(matches).containsExactly(MISSING, MISSING, MISSING, INCORRECT, INCORRECT)
    }
}
