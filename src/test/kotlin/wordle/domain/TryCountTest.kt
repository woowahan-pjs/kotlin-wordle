package wordle.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TryCountTest {

    @Test
    fun `6번의 기회동안 맞추지 못하면 패배한다`() {
        val givenTryCount = 7

        val message = assertThrows<IllegalArgumentException> {
            TryCount(givenTryCount)
        }.message

        Assertions.assertThat(message).isEqualTo("더 이상 입력할 수 없습니다.")
    }
}
