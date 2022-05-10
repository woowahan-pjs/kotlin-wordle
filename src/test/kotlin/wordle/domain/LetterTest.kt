package wordle.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LetterTest {

    @Test
    @DisplayName("Letter는 알파벳 소문자여야한다.")
    fun constructWithLowerAlphabet() {
        shouldThrow<IllegalArgumentException> { Letter('1') }
    }

    @Test
    @DisplayName("글자가 일치하면 true를 반환한다.")
    fun isSameLetter_true() {
        val letterA = Letter('a')

        val letterB = Letter('a')

        letterA shouldBe letterB
    }

    @Test
    @DisplayName("글자가 일치하지 않으면 false를 반환한다.")
    fun isSameLetter_false() {
        val letterA = Letter('a')

        val letterB = Letter('b')

        letterA shouldNotBe letterB
    }
}