package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class WordsTest {

    @Test
    fun 오늘의_단어를_선택() {
        val date = LocalDate.of(2022, 5, 12)

        assertThat(date.pickTodayWord()).isEqualTo("fetus")
    }
}
