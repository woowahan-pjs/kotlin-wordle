package wordle.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WordTest {

    @Test
    @DisplayName("단어는 5글자여야 한다.")
    fun constructor() {
        shouldThrow<IllegalArgumentException> { (Word("word")) }
    }

    @Test
    @DisplayName("유효한 단어여야 한다.")
    fun constructorWithValidWord() {
        shouldNotThrow<IllegalArgumentException> { Word("cigar") }
    }

    @Test
    @DisplayName("유효하지 않은 단어일 경우 예외를 발생시킨다.")
    fun constructorWithInvalidWord() {
        shouldThrow<IllegalArgumentException> { Word("abcde") }
    }
}