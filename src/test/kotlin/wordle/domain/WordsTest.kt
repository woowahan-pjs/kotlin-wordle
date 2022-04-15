package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import wordle.domain.Answers.Companion.WORDS

class WordsTest {

    @Test
    fun `오늘의 정답이 맞는지 확인한다`() {
        val words = WORDS
        val answer = words.findAnswer()

        println(answer)

        assertThat(answer).isNotNull
    }
}
