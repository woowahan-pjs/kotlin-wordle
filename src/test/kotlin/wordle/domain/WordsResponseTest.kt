package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class WordsResponseTest {

    @Test
    @DisplayName("/words.txt의 단어를 받아온다.")
    fun getWordsResponse() {
        val wordsResponse = WordsResponse("words.txt")
        val words = wordsResponse.words
        assertThat(words.size).isEqualTo(WORDS_SIZE)
    }

    companion object {
        private const val WORDS_SIZE = 2309
    }
}
