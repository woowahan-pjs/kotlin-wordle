package wordle.domain

import io.kotest.matchers.collections.shouldContainExactly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AnswerTest {

    @Test
    @DisplayName("완전히 일치하는 글자들과 틀린 글자가 존재하는 예측을 비교한다.")
    fun compareWithGreenAndGray() {
        val answer = Answer(Word("cigar"))

        answer.compare(Word("clear")) shouldContainExactly
            listOf(Color.GREEN, Color.GRAY, Color.GRAY, Color.GREEN, Color.GREEN)
    }

    @Test
    @DisplayName("완전히 일치하는 글자들만 존재하는 예측을 비교한다. - 정답인 경우")
    fun compareWithAllGreens() {
        val answer = Answer(Word("cigar"))

        answer.compare(Word("cigar")) shouldContainExactly
            listOf(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN)
    }

    @Test
    @DisplayName("모든 종류의 글자들이 존재하는 예측을 비교한다.")
    fun compareWithAllColors() {
        val answer = Answer(Word("spill"))

        answer.compare(Word("hello")) shouldContainExactly
            listOf(Color.GRAY, Color.GRAY, Color.YELLOW, Color.GREEN, Color.GRAY)
    }

    @Test
    @DisplayName("위치만 일치하는 글자가 정답에 더 많은 경우 예측을 비교한다.")
    fun compareWithAnswerMoreIncludeYellow() {
        val answer = Answer(Word("front"))

        answer.compare(Word("totem")) shouldContainExactly
            listOf(Color.YELLOW, Color.YELLOW, Color.GRAY, Color.GRAY, Color.GRAY)
    }

    @Test
    @DisplayName("위치만 일치하는 글자가 여러 개이면서 입력과 정답에 개수가 같은 경우 예측을 비교한다.")
    fun compareWithAnswerSameIncludeYellow() {
        val answer = Answer(Word("totem"))

        answer.compare(Word("start")) shouldContainExactly
            listOf(Color.GRAY, Color.YELLOW, Color.GRAY, Color.GRAY, Color.YELLOW)
    }

    @Test
    @DisplayName("위치만 일치하는 글자가 입력에 더 많은 경우 예측을 비교한다.")
    fun compareWithInputMoreIncludeYellow() {
        val answer = Answer(Word("witch"))

        answer.compare(Word("timid")) shouldContainExactly
            listOf(Color.YELLOW, Color.GREEN, Color.GRAY, Color.GRAY, Color.GRAY)
    }

    @Test
    @DisplayName("정답 글자가 먼저 존재하면서, 위치만 일치하는 글자가 그 뒤에 존재하는 경우 예측을 비교한다.")
    fun compareWithGreenFirstSoNotYellow() {
        val answer = Answer(Word("marry"))

        answer.compare(Word("mimic")) shouldContainExactly
            listOf(Color.GREEN, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY)
    }

    @Test
    @DisplayName("전부 틀린 글자들이 존재하는 예측을 비교한다.")
    fun compareWithAllGrays() {
        val answer = Answer(Word("parry"))

        answer.compare(Word("biome")) shouldContainExactly
            listOf(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY)
    }

    @Test
    @DisplayName("전부 위치만 일치하는 글자들이 존재하는 예측을 비교한다.")
    fun compareWithAllYellows() {
        val answer = Answer(Word("parse"))

        answer.compare(Word("spear")) shouldContainExactly
            listOf(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW)
    }
}
