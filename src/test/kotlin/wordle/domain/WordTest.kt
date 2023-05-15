package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import wordle.domain.MatchResult.GRAY
import wordle.domain.MatchResult.GREEN
import wordle.domain.MatchResult.YELLOW

class WordTest {

    @ParameterizedTest
    @ValueSource(strings = ["hell", "helloo"])
    fun `단어는 5글자가 아니면 예외`(string: String) {
        // when & then
        assertThrows<IllegalArgumentException> { Word.from(string) }
    }

    @Test
    fun `단어가 알파벳이 아니면 예외`() {
        // when & then
        assertThrows<IllegalArgumentException> { Word.from("가나다라마") }
    }

    @Test
    fun `words에 존재하지 않는 단어면 예외`() {
        // when & then
        assertThrows<IllegalArgumentException> { Word.from("kkkkk") }
    }

    @Test
    fun `두 단어를 비교하여 비교 결과를 반환한다`() {
        // given
        val answer: Word = Word.from("spill")
        val target: Word = Word.from("hello")

        // when
        val matchResult: Result = answer.match(target)

        // then
        // ⬜⬜🟨🟩⬜
        assertThat(matchResult.matchResults).containsSequence(
            GRAY, GRAY, YELLOW, GREEN, GRAY
        )
    }
}
