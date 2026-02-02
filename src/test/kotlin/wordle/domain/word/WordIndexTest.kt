package wordle.domain.word

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate

class WordIndexTest {
    @ParameterizedTest
    @MethodSource("expectedWords")
    @DisplayName("오늘의 단어 순번은 전달받은 날짜 빼기 2021년 6월 19일 나누기 배열의 크기이다")
    fun test01(
        date: LocalDate,
        arraySize: Int,
        expected: Long,
    ) {
        // arrange & act
        val sut = WordIndex.create(date, arraySize)

        // assert
        assertThat(sut.value).isEqualTo(expected)
    }

    @Test
    @DisplayName("전달받은 날짜가 2021년 6월 19일 이전이면 순번은 0이다")
    fun test02() {
        // arrange
        val date = LocalDate.of(2020, 6, 19)
        val arraySize = 20

        // act
        val sut = WordIndex.create(date, arraySize)

        // assert
        assertThat(sut.value).isEqualTo(0)
    }

    companion object {
        @JvmStatic
        fun expectedWords() =
            listOf(
                Arguments.of(
                    LocalDate.of(2026, 1, 7),
                    20,
                    3,
                ),
                Arguments.of(
                    LocalDate.of(2021, 6, 19),
                    20,
                    0,
                ),
            )
    }
}
