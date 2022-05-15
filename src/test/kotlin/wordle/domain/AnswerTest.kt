package wordle.domain

import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AnswerTest {

    @Test
    @DisplayName("완전히 일치하는 글자들과 틀린 글자가 존재하는 예측을 비교한다.")
    fun compareWithGreenAndGray() {
        val answer = Answer(Word("cigar"))

        answer.compare(Word("clear")) shouldContainAll listOf(
            Color.GREEN,
            Color.GRAY,
            Color.GRAY,
            Color.GREEN,
            Color.GREEN
        )
    }

    @Test
    @DisplayName("완전히 일치하는 글자들만 존재하는 예측을 비교한다. - 정답인 경우")
    fun compareWithAllGreens() {
        val answer = Answer(Word("cigar"))

        answer.compare(Word("cigar")) shouldContainAll
            listOf(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN)
    }

    @Test
    @DisplayName("모든 종류의 글자들이 존재하는 예측을 비교한다.")
    fun compareWithAllColors() {
        val answer = Answer(Word("spill"))

        answer.compare(Word("hello")) shouldContainAll
            listOf(Color.GRAY, Color.GRAY, Color.YELLOW, Color.GREEN, Color.GRAY)
    }

    @Test
    @DisplayName("위치만 일치하는 글자와 틀린 글자들이 존재하는 예측을 비교한다.")
    fun compareWithYellowAndGray1() {
        val answer = Answer(Word("front"))

        answer.compare(Word("totem")) shouldContainAll
            listOf(Color.YELLOW, Color.YELLOW, Color.GRAY, Color.GRAY, Color.GRAY)
    }

    @Test
    @DisplayName("위치만 일치하는 글자들과 틀린 글자들이 존재하는 예측을 비교한다.")
    fun compareWithYellowAndGray2() {
        val answer = Answer(Word("totem"))

        answer.compare(Word("start")) shouldContainAll
            listOf(Color.GRAY, Color.YELLOW, Color.GRAY, Color.GRAY, Color.YELLOW)
    }

    @Test
    @DisplayName("전부 틀린 글자들이 존재하는 예측을 비교한다.")
    fun compareWithAllGrays() {
        val answer = Answer(Word("parry"))

        answer.compare(Word("biome")) shouldContainAll
            listOf(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY)
    }

    @Test
    @DisplayName("전부 위치만 일치하는 글자들이 존재하는 예측을 비교한다.")
    fun compareWithAllYellows() {
        val answer = Answer(Word("parse"))

        answer.compare(Word("spear")) shouldContainAll
            listOf(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW)
    }

    @Test
    @DisplayName("test.")
    fun test1() {
        val answer = Answer(Word("witch"))

        answer.compare(Word("timid")) shouldContainAll
            listOf(Color.YELLOW, Color.GREEN, Color.GRAY, Color.GRAY, Color.GRAY)
    }
}