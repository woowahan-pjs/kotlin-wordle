package edu.nextstep.wordle.application.wordle

import edu.nextstep.wordle.application.wordle.window.Match
import edu.nextstep.wordle.application.wordle.window.WindowResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WordleTest {
    @Test
    fun `입력이 사전에 존재하지 않는 단어라면 재시도 응답을 한다`() {
        //given
        val wordle = Wordle(
            target = Word.create("match"),
            wordFinder = { false }
        )

        val notExist = Word.create("asdfa")

        //when
        val answer = wordle.input(notExist)

        //then
        assertThat(answer).isInstanceOf(WordleAnswer.Retry::class.java)
        assertThat(answer.wordle).isEqualTo(wordle)
        assertThat((answer as WordleAnswer.Retry).message).isEqualTo("사전에 없는 단어($notExist)입니다.")
    }

    @Test
    fun `입력이 정상적으로 처리되면 결과와 함께 반환한다`() {
        //given
        val wordFinder = { _: Word -> true }

        val wordle = Wordle(
            target = Word.create("match"),
            wordFinder = wordFinder
        )

        val input = Word.create("matle")

        //when
        val answer = wordle.input(input)

        //then
        assertThat(answer).isInstanceOf(WordleAnswer.Right::class.java)

        assertThat(answer.wordle).isEqualTo(Wordle(
            target = Word.create("match"),
            wordResult = listOf(
                WordResult(
                    1,
                    listOf(
                        WindowResult(0, Match.PERFECT),
                        WindowResult(1, Match.PERFECT),
                        WindowResult(2, Match.PERFECT),
                        WindowResult(3, Match.MISS),
                        WindowResult(4, Match.MISS),
                    ),
                ),
            ),
            wordFinder = wordFinder,
        ))

        assertThat(answer.wordle.round).isEqualTo(2)
    }

    @Test
    fun `성공여부를 확인한다`() {
        //given
        val wordle = Wordle(
            target = Word.create("match"),
            wordFinder = { true }
        )

        val input = Word.create("match")

        //when
        val answer = wordle.input(input)

        //then
        assertThat(answer).isInstanceOf(WordleAnswer.Right::class.java)

        assertThat(answer.wordle.isSuccess()).isTrue
    }
}
