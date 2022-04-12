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
}
