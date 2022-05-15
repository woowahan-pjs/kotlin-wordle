package wordle.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WordTest {

    @Test
    @DisplayName("단어는 5글자여야 한다.")
    fun constructor() {
        shouldThrow<IllegalArgumentException> { (Word("word")) }
            .shouldHaveMessage("단어는 5글자여야 합니다.")
    }

    @Test
    @DisplayName("단어는 소문자로 이루어져야 한다.")
    fun constructorWithLowercaseWord() {
        shouldThrow<IllegalArgumentException> { Word("CIGAR") }
            .shouldHaveMessage("단어는 소문자로 이루어져야 합니다.")
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
            .shouldHaveMessage("유효하지 않은 단어입니다.")
    }

    @Test
    @DisplayName("다른 단어와 같은 인덱스로 비교한다")
    fun compareBySameIndex() {
        val wordA = Word("cigar")
        val wordB = Word("clear")

        wordA.compareByIndex(wordB, 0) shouldBe true
    }

    @Test
    @DisplayName("다른 단어와 다른 인덱스로 비교한다")
    fun compareByDifferentIndex() {
        val wordA = Word("cigar")
        val wordB = Word("clear")

        wordA.compareByIndex(wordB, 0, 1) shouldBe false
    }
}
