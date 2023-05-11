package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class WordsTest {

    @Test
    @DisplayName("정답단어를 가져와야 한다.")
    fun getAnswerWord() {
        val wordsReader = WordsReader("words.txt")
        val words = Words(wordsReader.words)
        val answerWord = words.findAnswer(LocalDate.of(2021, 6, 19))
        assertThat(answerWord).isEqualTo(Word("cigar"))
    }

    @Test
    @DisplayName("words에 포함된 단어이면 true 여야 한다.")
    fun shouldContainsWords() {
        // given
        val wordsReader = WordsReader("words.txt")
        val words = Words(wordsReader.words)
        val value = "rebut"
        // when
        val hasWord = words.contains(Word(value))
        // then
        assertThat(hasWord).isTrue
    }

    @Test
    @DisplayName("words에 포함된 단어가 아니면 false 여야 한다.")
    fun failShouldContainsWords() {
        // given
        val wordsReader = WordsReader("words.txt")
        val words = Words(wordsReader.words)
        val value = "xxxxx"
        // when
        val hasWord = words.contains(Word(value))
        // then
        assertThat(hasWord).isFalse
    }
}
