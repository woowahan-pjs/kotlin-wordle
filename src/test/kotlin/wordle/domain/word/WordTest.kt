package wordle.domain.word

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WordTest {
    @ParameterizedTest
    @MethodSource("expectedWords")
    @DisplayName("입력된 인덱스와 입력된 캐릭터가, Word와 동일한지 판단한다")
    fun test01(
        index: Int,
        char: Char,
        expected: Boolean,
    ) {
        // arrange
        val input: Word = Word("APPLE")

        // act
        val sut: Boolean = input.matchesAt(index, char)

        // assert
        assertThat(sut).isEqualTo(expected)
    }

    @Test
    @DisplayName("인덱스에서 벗어난 값이 들어오면 오류가 발생한다.")
    fun test02() {
        // arrange
        val input: Word = Word("APPLE")

        // act & assert
        assertThatThrownBy { input.matchesAt(10, 'A') }.isInstanceOf(IllegalArgumentException::class.java)
    }

    companion object {
        @JvmStatic
        fun expectedWords() =
            listOf(
                Arguments.of(0, 'A', true),
                Arguments.of(0, 'B', false),
            )
    }
}
