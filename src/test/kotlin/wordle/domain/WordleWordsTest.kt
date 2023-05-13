package wordle.domain

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WordleWordsTest {

    @Test
    fun `contains 테스트`() {
        val wordleWords = WordleWords(listOf(Word("apple"), Word("spill")))
        wordleWords.contains(Word("apple")) shouldBe true
    }

    @Test
    fun `날짜에 따른 그날의 정답 단어를 반환한다`() {
        val wordleWords = WordleWords(listOf(Word("apple"), Word("spill")))

        val actual = wordleWords.getTodaysWord(LocalDate.of(2021, 6, 20))

        assertThat(actual).isEqualTo(Word("spill"))
    }
}
