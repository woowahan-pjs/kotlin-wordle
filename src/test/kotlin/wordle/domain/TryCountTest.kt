package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow

class TryCountTest {
    @Test
    fun `시도 가능 횟수를 생성한다 - 최대 시도 가능 횟수를 가진다`() {
        val actual = TryCount()

        assertThat(actual).isEqualTo(TryCount(MAX_TRY_COUNT))
    }

    @Test
    fun `시도한 횟수를 반환한다`() {
        val tryCount = TryCount()

        val actual = tryCount.attempts

        assertThat(actual).isZero()
    }

    @Test
    fun `시도 가능 횟수가 있다면 true 를 반환한다`() {
        val tryCount = TryCount()
        tryCount.minus()

        val actual = tryCount.isRemainder()

        assertThat(actual).isTrue()
    }

    @Test
    fun `시도 가능 횟수가 없다면 false 를 반환한다`() {
        val tryCount = TryCount(0)

        val actual = tryCount.isRemainder()

        assertThat(actual).isFalse()
    }

    @Test
    fun `시도 가능 횟수를 1회 차감하면 시도한 횟수는 1회 증가한다`() {
        val tryCount = TryCount(6)

        assertDoesNotThrow { tryCount.minus() }

        assertAll(
            { assertThat(tryCount).isEqualTo(TryCount(5)) },
            { assertThat(tryCount.attempts).isEqualTo(1) },
        )
    }

    @Test
    fun `시도 가능 횟수가 0 보다 작으면, 예외를 반환한다`() {
        val tryCount = TryCount(0)

        assertThatThrownBy { tryCount.minus() }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("시행 횟수는 0보다 작을 수 없습니다.")
    }
}
