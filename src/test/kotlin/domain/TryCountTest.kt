package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores::class)
class TryCountTest {

    @Test
    fun 시도회수는_양수를_받아_생성된다() {
        // given
        val count: Int = 10

        // expect
        assertDoesNotThrow { TryCount(count) }
    }

    @Test
    fun 시도회수는_음수를_받으면_예외가_발생한다() {
        // given
        val count: Int = -1

        // expect
        assertThrows(IllegalArgumentException::class.java) { TryCount(count) }
    }

    @Test
    fun 시도회수는_올라갈_수_있다() {
        // given
        val tryCount1 = TryCount()
        val tryCount2 = TryCount(1)

        // when
        val tryCount3: TryCount = tryCount1.plus()

        // then
        assertEquals(tryCount2, tryCount3)
    }
}
