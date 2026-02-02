package wordle.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import wordle.domain.result.Result
import wordle.domain.result.Results
import wordle.domain.word.Answer
import wordle.domain.word.Word

class WordleTest {
    val answer = Word("apple")
    val resolvedAnswer = Answer(answer)

    @Test
    @DisplayName("전달받은 단어로 결과를 반환한다")
    fun test01() {
        // arrange
        val wordle = Wordle(resolvedAnswer)
        val word = Word("testt")

        val expected: Results =
            Results(
                listOf(
                    Result.ABSENT,
                    Result.PRESENT,
                    Result.ABSENT,
                    Result.ABSENT,
                    Result.ABSENT,
                ),
            )

        // act
        val sut: Results = wordle.round(word)

        // assert
        assertThat(sut).isEqualTo(expected)
    }
}
