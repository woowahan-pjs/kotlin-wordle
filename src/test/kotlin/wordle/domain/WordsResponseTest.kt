package wordle.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class WordsResponseTest {

    @Test
    @DisplayName("/words.txt의 단어를 받아온다.")
    fun getWordsResponse() {
        val wordsReader = WordsReader("words.txt")
        val words = wordsReader.words
        words.size shouldBe WORDS_SIZE
    }

    companion object {
        private const val WORDS_SIZE = 2309
    }
}
