package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import wordle.domain.TileColor.GRAY
import wordle.domain.TileColor.GREEN
import wordle.domain.TileColor.YELLOW

class WordleComparatorTest {

    private val wordleComparator = WordleComparator()

    @Test
    fun `모든 문자가 일치하면 모두 초록색을 반환한다`() {
        val actual = wordleComparator.getTileColors(Word("apple"), Word("apple"))

        assertThat(actual.result).containsExactly(
            GREEN,
            GREEN,
            GREEN,
            GREEN,
            GREEN
        )
    }

    @Test
    fun `단어가 한 글자도 일치하지 않는 경우 모두 회색을 반환한다`() {
        val actual = wordleComparator.getTileColors(Word("spill"), Word("abcde"))

        assertThat(actual.result).containsExactly(
            GRAY,
            GRAY,
            GRAY,
            GRAY,
            GRAY
        )
    }

    @Test
    fun `문자가 맞으면은 초록색 존재하지 않으면 회색을 반환한다`() {
        val actual = wordleComparator.getTileColors(Word("spill"), Word("spaal"))

        assertThat(actual.result).containsExactly(
            GREEN,
            GREEN,
            GRAY,
            GRAY,
            GREEN
        )
    }

    @Test
    fun `문자는 존재하지만 위치가 맞지 않으면 노란색을 반환한다`() {
        val actual = wordleComparator.getTileColors(Word("abcde"), Word("bcdea"))

        assertThat(actual.result).containsExactly(
            YELLOW,
            YELLOW,
            YELLOW,
            YELLOW,
            YELLOW,
        )
    }

    @Test
    fun `정답이 spill이고 문자가 hello인 경우 회회노초회를 반환한다`() {
        val actual = wordleComparator.getTileColors(Word("spill"), Word("hello"))

        assertThat(actual.result).containsExactly(
            GRAY,
            GRAY,
            YELLOW,
            GREEN,
            GRAY
        )
    }

    @Test
    fun `정답이 spill이고 문자가 ablll인 경우 회회회초초를 반환한다`() {
        val actual = wordleComparator.getTileColors(Word("spill"), Word("ablll"))

        assertThat(actual.result).containsExactly(
            GRAY,
            GRAY,
            GRAY,
            GREEN,
            GREEN,
        )
    }

    @Test
    fun `정답이 pplab이고 문자가 apppc인 경우 노초노회회를 반환한다`() {
        val actual = wordleComparator.getTileColors(Word("pplab"), Word("apppc"))

        assertThat(actual.result).containsExactly(
            YELLOW,
            GREEN,
            YELLOW,
            GRAY,
            GRAY
        )
    }
}
