package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

class GameTest {
    @Test
    fun `게임은 6번을 입력할 기회가 주어진다`() {
        // given
        val input = TestInput(LinkedList(listOf("hello", "hello", "hello", "hello", "hello", "hello")))
        val game = Game(input)

        // when
        game.start()

        // then
        assertThat(input.result).isEmpty()
    }

    @Test
    fun `게임은 7번 이상 입력시 6번만 입력 된다`() {
        // given
        val input = TestInput(LinkedList(listOf("hello", "hello", "hello", "hello", "hello", "hello", "hello")))
        val game = Game(input)

        // when
        game.start()

        // then
        assertThat(input.result).hasSize(1)
    }
}

class TestInput(val result: Queue<String>): Input {
    override fun read(): Tiles {
        return Tiles.of(result.poll())
    }
}