package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class WordsTest {

    @Test
    fun `오늘의 단어를 선택`() {
        val date = LocalDate.of(2022, 5, 12)

        assertThat(Words.pick(date)).isEqualTo("fetus")
    }
}
