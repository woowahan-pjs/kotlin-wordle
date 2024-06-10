package study

import io.kotest.matchers.collections.shouldContainExactly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import study.operator.Point

class OperatorTest {
    @Test
    @DisplayName("좌표의 덧셈 연산을 제공한다.")
    fun createPlusOverloading() {
        val point = Point(0, 2) + Point(1, 3)

        listOf(point["x"], point["y"]) shouldContainExactly (listOf(1, 5))
    }

    @Test
    @DisplayName("좌표의 뺄셈 연산을 제공한다.")
    fun createMinusOverloading() {
        val point = Point(0, 2) - Point(1, 3)

        listOf(point["x"], point["y"]) shouldContainExactly (listOf(-1, -1))
    }
}
