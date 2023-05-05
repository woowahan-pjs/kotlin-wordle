package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WordleGameTest {
    @Test
    fun `날짜에 따른 그날의 정답 단어를 반환한다`() {
        val game = WordleGame(
            WordleWords(listOf(Word("apple"), Word("spill")))
        )

        val actual = game.getTodaysWord(LocalDate.of(2021, 6, 20))

        assertThat(actual).isEqualTo(Word("spill"))
    }
}
