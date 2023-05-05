package wordle.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

class WordleWordsTest {

    @Test
    fun `get 테스트`() {
        val wordleWords = WordleWords(listOf(Word("apple"), Word("spill")))
        assertAll(
            { wordleWords.getWord(0) shouldBe Word("apple") },
            { wordleWords.getWord(1) shouldBe Word("spill") },
        )
    }

    @Test
    fun `contains 테스트`() {
        val wordleWords = WordleWords(listOf(Word("apple"), Word("spill")))
        wordleWords.contains(Word("apple")) shouldBe true
    }
}
