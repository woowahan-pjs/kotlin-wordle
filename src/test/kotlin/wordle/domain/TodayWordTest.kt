package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TodayWordTest {
    @Test
    fun `(성공) 오늘의 단어는 오늘 날짜를 입력받아 단어를 생성한다`() {
        val today = LocalDate.of(2021, 6, 19)

        val actual = TodayWord(today)

        assertThat(actual).isEqualTo(Word("hello"))
    }

    @Test
    fun `(성공) 오늘의 단어는 매일 바뀐다`() {
        val criterionDate = LocalDate.of(2024, 6, 17)
        val wordsSet = mutableSetOf<Word>()

        repeat(365) {
            val currentDate = criterionDate.plusDays(it.toLong())
            val word = TodayWord(currentDate)
            wordsSet.add(word)
        }

        assertThat(wordsSet).hasSize(365)
    }
}
