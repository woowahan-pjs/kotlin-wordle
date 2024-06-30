package wordle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import wordle.domain.Word

internal class WordTest {

    @ParameterizedTest
    @ValueSource(strings = ["-1", "1", " ", "w!", " ;fs"])
    fun `모두 영문으로 구성된다`(value: String) {
        //when
        val errorResponse = assertThrows<IllegalArgumentException> { Word(value) }

        ///then
        assertThat(errorResponse.message).isEqualTo("영문만 입력해야합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["test", "hi", "h", "tttttt"])
    fun `5글자가 아니면 IllegalArgumentException 예외가 발생한다`(value: String) {
        //when
        val errorResponse = assertThrows<IllegalArgumentException> { Word(value) }

        ///then
        assertThat(errorResponse.message).isEqualTo("5글자여야 합니다.")
    }

    @Test
    fun `영문 대문자는 소문자로 치환된다`() {
        assertThat(Word("Hello")).isEqualTo(Word("hello"))
    }
}
