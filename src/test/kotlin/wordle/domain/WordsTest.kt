package wordle.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class WordsTest {

    @Test
    fun `정답단어를 가져와야 한다`() {
        val wordsReader = WordsReader("words.txt")
        val words = Words(wordsReader.words)
        val answerWord = words.findAnswer(LocalDate.of(2021, 6, 19))

        answerWord shouldBe Word("cigar")
    }

    @Test
    fun `words에 포함된 단어이면 true 여야 한다`() {
        // given
        val wordsReader = WordsReader("words.txt")
        val words = Words(wordsReader.words)
        val value = "rebut"

        // when
        val hasWord = words.contains(Word(value))

        // then
        hasWord shouldBe true
    }

    @Test
    fun `words에 포함된 단어가 아니면 false 여야 한다`() {
        // given
        val wordsReader = WordsReader("words.txt")
        val words = Words(wordsReader.words)
        val value = "xxxxx"

        // when
        val hasWord = words.contains(Word(value))

        // then
        hasWord shouldBe false
    }
}
