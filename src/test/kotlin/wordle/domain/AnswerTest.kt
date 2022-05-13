package wordle.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import wordle.domain.Mark.*

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

    @Test
    fun 답안과_정답을_비교_CASE_중복되는_문자_중_하나만_일치_할_때() {
        val answer = Answer("groom")

        assertThat(answer.compareToWord("goose"))
            .isEqualTo(listOf(EXACT, NONE, EXACT, EXIST, NONE))
    }

    @Test
    fun 답안과_정답을_비교_CASE_중복되는_문자가_존재하지만_정답의_개수가_더_많을_때() {
        val answer = Answer("eerie")

        assertThat(answer.compareToWord("sheen"))
            .isEqualTo(listOf(EXIST, EXIST, NONE, NONE, NONE))
    }
}
