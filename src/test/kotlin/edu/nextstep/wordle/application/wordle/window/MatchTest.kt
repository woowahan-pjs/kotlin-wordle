package edu.nextstep.wordle.application.wordle.window

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class MatchTest {
    @ParameterizedTest
    @EnumSource(value = Match::class, names = ["PERFECT", "WRONG", "MISS"])
    fun `현재 값이 PERFECT 면 갱신할 필요가 없다`(input: Match) {
        //given
        val now = Match.PERFECT

        //when
        val updatable = now.updatable(input)

        //then
        assertThat(updatable).isFalse
    }

    @ParameterizedTest
    @EnumSource(value = Match::class, names = ["PERFECT"], mode = EnumSource.Mode.EXCLUDE)
    fun `현재값이 WRONG 이라면 입력이 PERFECT 가 아니면 갱신할 필요가 없다`(input: Match) {
        //given
        val now = Match.WRONG

        //when
        val updatable = now.updatable(input)

        //then
        assertThat(updatable).isFalse
    }

    @ParameterizedTest
    @EnumSource(value = Match::class, names = ["PERFECT"])
    fun `현재값이 WRONG 이라면 입력이 PERFECT 면 갱실할 수 있다`(input: Match) {
        //given
        val now = Match.WRONG

        //when
        val updatable = now.updatable(input)

        //then
        assertThat(updatable).isTrue
    }

    @ParameterizedTest
    @EnumSource(value = Match::class, names = ["MISS"], mode = EnumSource.Mode.EXCLUDE)
    fun `현재값이 MISS 라면 입력이 MISS가 아니면 갱신할 필요가 없다`(input: Match) {
        //given
        val now = Match.MISS

        //when
        val updatable = now.updatable(input)

        //then
        assertThat(updatable).isTrue
    }
}
