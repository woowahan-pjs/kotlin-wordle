package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WordleTest {
    val answer = Word("apple")
    val validator: WordleWordValidator = WordleWordValidator(WordBook(listOf(Word("testt"))))

    @Test
    @DisplayName("전달받은 단어로 결과를 반환합니다.")
    fun test01() {
        // arrange
        val wordle = Wordle(validator, answer)
        val word = "testt"

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
