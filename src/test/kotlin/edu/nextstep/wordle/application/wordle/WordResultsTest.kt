package edu.nextstep.wordle.application.wordle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WordResultsTest {
    @Test
    fun `결과가 없다면 실패로 간주한다`() {
        //given
        val result = WordResults(emptyList())

        //when
        val success = result.isSuccess()

        //then
        assertThat(success).isFalse
    }
}
