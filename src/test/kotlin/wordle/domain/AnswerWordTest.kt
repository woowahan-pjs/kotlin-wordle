package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AnswerWordTest {
    @Test
    fun `(성공) 답안 단어는 문자열을 입력받아 단어를 생성한다`() {
        val actual = AnswerWord("hello")

        assertThat(actual).isEqualTo(Word("hello"))
    }
}
