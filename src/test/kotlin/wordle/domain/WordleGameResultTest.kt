package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import wordle.domain.TileColor.GREEN

class WordleGameResultTest {

    @Test
    fun `타일이 모두 초록이면 isCorrect는 true를 반환한다`() {
        val wordleGameResult = WordleGameResult(
            listOf(
                GREEN,
                GREEN,
                GREEN,
                GREEN,
                GREEN,
                GREEN,
            )
        )
        assertThat(wordleGameResult.isCorrect()).isTrue
    }
}
