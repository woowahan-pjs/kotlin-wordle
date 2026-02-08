package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class WordBookTest {
    private val validator = AnswerWordValidator(3)

    @Test
    @DisplayName("단어장의 크기를 구합니다.")
    fun test01() {
        // arrange
        val wordBook: WordBook = WordBook.from(validator, listOf("AAA", "BBB"))

        // act
        val sut: Int = wordBook.getAllCount()

        // assert
        assertThat(sut).isEqualTo(2)
    }

    @Nested
    @DisplayName("특정 인덱스를 이용하여 단어를 가져올 때,")
    inner class test02 {
        @Test
        @DisplayName("인덱스 값이 0보다 작으면, 예외가 발생합니다.")
        fun test01() {
            // arrange
            val wordBook: WordBook = WordBook.from(validator, listOf("AAA", "BBB"))

            val index: Int = -1

            // act & assert
            assertThatThrownBy { wordBook.getWord(index) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageStartingWith("index가 범위를 벗어났습니다.")
        }

        @Test
        @DisplayName("인덱스 값이 전체 단어장 갯수를 넘기면, 예외가 발생합니다.")
        fun test02() {
            // arrange
            val wordBook: WordBook = WordBook.from(validator, listOf("AAA"))

            val index: Int = 30

            // act & assert
            assertThatThrownBy { wordBook.getWord(index) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageStartingWith("index가 범위를 벗어났습니다.")
        }

        @Test
        @DisplayName("인덱스에 맞는 단어를 가져옵니다.")
        fun test03() {
            // arrange
            val input: String = "AAA"

            val wordBook: WordBook = WordBook.from(validator, listOf(input))
            val index: Int = 0

            val expected: Word = Word(input)

            // act
            val sut: Word = wordBook.getWord(index)

            // assert
            assertThat(sut).isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("입력된 단어가 단어장에 존재하는지 판단할 때,")
    inner class test03 {
        @Test
        @DisplayName("존재하는 단어일 경우 true를 반환합니다.")
        fun test01() {
            // arrange
            val input: String = "AAA"
            val wordBook: WordBook = WordBook.from(validator, listOf(input))

            // act
            val sut: Boolean = wordBook.exists(input)

            // assert
            assertThat(sut).isTrue
        }

        @Test
        @DisplayName("존재하지 않는 단어인 경우 false를 반환합니다.")
        fun test02() {
            // arrange
            val wordBook: WordBook = WordBook.from(validator, listOf("AAA"))
            val input = "BBB"

            // act
            val sut: Boolean = wordBook.exists(input)

            // assert
            assertThat(sut).isFalse
        }
    }
}
