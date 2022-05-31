package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class WordsReaderTest {

    @Test
    @DisplayName("/words.txt의 단어를 받아온다.")
    fun getWordsResponse() {
        val wordsReader = WordsReader("words.txt")
        val words = wordsReader.words
        assertThat(words.size).isEqualTo(WORDS_SIZE)
    }

    companion object {
        private const val WORDS_SIZE = 2309
    }
}
