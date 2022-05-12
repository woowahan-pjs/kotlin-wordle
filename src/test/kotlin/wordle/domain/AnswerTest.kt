package wordle.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class AnswerTest {

    @Test
    fun 글자길이가_5가_아닌_경우_예외발생() {
        assertThrows<IllegalArgumentException> { Answer("abcdef") }
            .shouldHaveMessage("[ERROR] 부적절한 글자 길이입니다.")
    }

    @Test
    fun 주어진_단어목록에_존재하지_않는_경우_예외발생() {
        assertThrows<IllegalArgumentException> { Answer("abcde") }
            .shouldHaveMessage("[ERROR] 목록에 존재하지 않는 단어입니다.")
    }
}
