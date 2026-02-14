package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WordsTest {

    @ParameterizedTest
    @CsvSource("0, queen", "1, chunk", "2, awake")
    fun `오늘의 정답이 맞는지 확인한다`(position: Int, expected: String) {
        val answers = Answer(MockingCreator(), Position(position))
        val answer = answers.createAnswer()

        assertThat(answer).isEqualTo(Word(expected))
    }
}
