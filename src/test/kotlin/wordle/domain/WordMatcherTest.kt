package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WordMatcherTest {

    @ParameterizedTest
    @CsvSource("i, 0", "n, 1", "t, 4")
    fun `답안과 정답의 한 단어가 위치와 스펠이 같은 경우 초록색이 된다`(alphabet: String, index: Int) {
        val wordMatcher = WordMatcher(Word("input"))

        assertThat(wordMatcher.match(alphabet, index)).isEqualTo(Tile.GREEN)
    }

    @ParameterizedTest
    @CsvSource("i, 2", "n, 3", "p, 4")
    fun `답안과 정답의 한 단어가 위치는 같지만 스펠이 다른 경우 노란색이 된다`(givenAlphabet: String, givenIndex: Int) {
        val wordMatcher = WordMatcher(Word("input"))

        val actual = wordMatcher.match(givenAlphabet, givenIndex)

        assertThat(actual).isEqualTo(Tile.YELLOW)
    }

    @Test
    fun `답안과 정답의 한 단어가 위치도 다르고 스펠도 다른 경우 회색이 된다`() {
        val wordMatcher = WordMatcher(Word("input"))
        val givenAlphabet = "a"
        val givenIndex = 4

        val actual = wordMatcher.match(givenAlphabet, givenIndex)

        assertThat(actual).isEqualTo(Tile.GRAY)
    }
}
