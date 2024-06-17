package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class TryCountTest {
    @Test
    fun `남은 시도 횟수를 반환한다`() {
        val tryCount = TryCount()

        val attempts = tryCount.attempts

        assertThat(attempts).isEqualTo(0)
    }

    @Test
    fun `남은 시도 횟수가 있다면 true 를 반환한다`() {
        val tryCount = TryCount()
        tryCount.minus()

        val remainder = tryCount.isRemainder()

        assertThat(remainder).isTrue()
    }

    @Test
    fun `남은 시도 횟수가 없다면 false 를 반환한다`() {
        val tryCount = TryCount(0)

        val remainder = tryCount.isRemainder()

        assertThat(remainder).isFalse()
    }

    @Test
    fun `남은 시도 횟수가 있다면 시도 횟수를 1 차감 한다`() {
        val tryCount = TryCount()

        assertDoesNotThrow { tryCount.minus() }
    }

    @Test
    fun `남은 시도 횟수가 없다면 시도 횟수를 차감하지 않고 예외를 반환한다`() {
        val tryCount = TryCount(0)

        assertThatThrownBy { tryCount.minus() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("시행 횟수는 0보다 작을 수 없습니다.")
    }
}
