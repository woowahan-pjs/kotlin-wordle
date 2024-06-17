package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TodayWordTest {
    @Test
    fun `오늘의 단어는 매일 바뀐다`() {
        val criterionDate = LocalDate.of(2023, 6, 9)
        val size = 100
        val wordsSet = mutableSetOf<Word>()

        repeat(size) {
            val currentDate = criterionDate.plusDays(it.toLong())
            val word = TodayWord(currentDate)
            wordsSet.add(word)
        }

        assertThat(wordsSet).hasSize(100)
    }
}
