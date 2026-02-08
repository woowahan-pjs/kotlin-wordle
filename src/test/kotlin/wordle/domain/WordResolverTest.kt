package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WordResolverTest {
    @ParameterizedTest
    @MethodSource("expected")
    @DisplayName("모든 단어가 맞을 때 정답을 판단합니다.")
    fun test01(
        answer: String,
        input: String,
        expected: List<Result>,
    ) {
        // arrange
        val answerWord: Word = Word(answer)
        val inputWord: Word = Word(input)

        val expectedResults: Results = Results(expected)

        // act
        val sut: Results = WordResolver(answerWord).check(inputWord)

        // assert
        assertThat(sut).isEqualTo(expectedResults)
    }

    companion object {
        @JvmStatic
        fun expected() =
            listOf(
                // 0) 기본 3종(이미 작성한 것)
                Arguments.of(
                    "APL",
                    "APL",
                    listOf(Result.CORRECT, Result.CORRECT, Result.CORRECT),
                ),
                Arguments.of(
                    "APL",
                    "QWE",
                    listOf(Result.ABSENT, Result.ABSENT, Result.ABSENT),
                ),
                Arguments.of(
                    "APL",
                    "LAP",
                    listOf(Result.PRESENT, Result.PRESENT, Result.PRESENT),
                ),
                // 1) 일부만 CORRECT
                Arguments.of(
                    "APL",
                    "AQW",
                    listOf(Result.CORRECT, Result.ABSENT, Result.ABSENT), // A만 맞음
                ),
                Arguments.of(
                    "APL",
                    "QPL",
                    listOf(Result.ABSENT, Result.CORRECT, Result.CORRECT), // P,L만 맞음
                ),
                Arguments.of(
                    "APL",
                    "APQ",
                    listOf(Result.CORRECT, Result.CORRECT, Result.ABSENT), // A,P만 맞음
                ),
                // 2) CORRECT + PRESENT 섞임 (서로 다른 문자)
                Arguments.of(
                    "APL",
                    "ALP",
                    listOf(Result.CORRECT, Result.PRESENT, Result.PRESENT), // A는 제자리, L/P는 위치만 틀림
                ),
                Arguments.of(
                    "APL",
                    "PAL",
                    listOf(Result.PRESENT, Result.PRESENT, Result.CORRECT), // L은 제자리, A/P는 위치만 틀림
                ),
                // 3) 중복 문자 관련 (정답에 중복 없음, 추측에 중복 있음)
                // 정답: A P L / 추측: A A A -> A 하나만 소비, 나머지는 ABSENT
                Arguments.of(
                    "APL",
                    "AAA",
                    listOf(Result.CORRECT, Result.ABSENT, Result.ABSENT),
                ),
                // 정답: A P L / 추측: P P P -> P 하나만 소비, 나머지는 ABSENT
                Arguments.of(
                    "APL",
                    "PPP",
                    listOf(Result.ABSENT, Result.CORRECT, Result.ABSENT),
                ),
                // 정답: A P L / 추측: L L L -> L 하나만 소비, 나머지는 ABSENT
                Arguments.of(
                    "APL",
                    "LLL",
                    listOf(Result.ABSENT, Result.ABSENT, Result.CORRECT),
                ),
                // 정답에 A는 1개뿐인데 추측에 A가 2개: 하나만 PRESENT/CORRECT 처리되고 나머지 ABSENT
                // 정답: A P L / 추측: Q A A -> 첫 A는 PRESENT(정답 0번 A), 두번째 A는 남은 A가 없으니 ABSENT
                Arguments.of(
                    "APL",
                    "QAA",
                    listOf(Result.ABSENT, Result.PRESENT, Result.ABSENT),
                ),
                // 4) 정답에 중복 있음, 추측에 중복 있음 (진짜 핵심 케이스)
                // 정답: A A L / 추측: A P A
                // 0:A CORRECT 소비, 2:A PRESENT(정답 1번 A) 소비
                Arguments.of(
                    "AAL",
                    "APA",
                    listOf(Result.CORRECT, Result.ABSENT, Result.PRESENT),
                ),
                // 정답: A A L / 추측: A A A
                // 0,1 CORRECT 소비, 2는 남은 A 없어서 ABSENT
                Arguments.of(
                    "AAL",
                    "AAA",
                    listOf(Result.CORRECT, Result.CORRECT, Result.ABSENT),
                ),
                // 정답: A A L / 추측: L A A
                // 0:L PRESENT(정답 2번 L), 1:A CORRECT, 2:A PRESENT(남은 A 하나)
                Arguments.of(
                    "AAL",
                    "LAA",
                    listOf(Result.PRESENT, Result.CORRECT, Result.PRESENT),
                ),
                // 정답: A B A / 추측: A A A
                // 0 CORRECT, 1 PRESENT(정답 2번 A), 2 ABSENT(남은 A 없음)
                Arguments.of(
                    "ABA",
                    "AAA",
                    listOf(Result.CORRECT, Result.ABSENT, Result.CORRECT),
                ),
                // 5) PRESENT 우선순위/소비량 확인 (중복 + 자리 경쟁)
                // 정답: A B A / 추측: B A B
                // 0:B PRESENT(정답 1번 B), 1:A PRESENT(정답 0/2 중 하나), 2:B 남은 B 없어서 ABSENT
                Arguments.of(
                    "ABA",
                    "BAB",
                    listOf(Result.PRESENT, Result.PRESENT, Result.ABSENT),
                ),
                // 정답: B A B / 추측: A B B
                // 2:B CORRECT 먼저 소비, 1:B PRESENT(정답 0번 B), 0:A PRESENT(정답 1번 A)
                Arguments.of(
                    "BAB",
                    "ABB",
                    listOf(Result.PRESENT, Result.PRESENT, Result.CORRECT),
                ),
            )
    }
}
