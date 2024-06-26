package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class WordleGameLogicTest {
    private lateinit var wordleGameLogic: WordleGameLogic

    @BeforeEach
    fun setUp() {
        val todayWord = Word("spill")
        wordleGameLogic = WordleGameLogic(todayWord)
    }

    @Nested
    inner class `단어 결과 중 글자 일치 상태가 하나만 다른 경우` {
        @Test
        fun `(성공) 하나의 글자 결과만 '부분 일치 글자'일 때 비교한다`() {
            val answerWord = Word("proxy")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.PRESENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                ).toWordResult(),
            )
        }

        @Test
        fun `(성공) 하나의 글자 결과만 '완전 일치 글자'일 때 비교한다 - '완전 일치 글자'부터 비교한다`() {
            val answerWord = Word("royal")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.CORRECT,
                ).toWordResult(),
            )
        }
    }

    @Nested
    inner class `답안 단어에 같은 글자가 있는 경우` {
        @Test
        fun `(성공) 두 번 같은 글자가 연속되는 답안 단어에서, 오늘의 단어와 한 글자만 '부분 일치 글자'일 때 비교한다`() {
            val answerWord = Word("truss")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.PRESENT,
                    LetterMatch.ABSENT,
                ).toWordResult(),
            )
        }

        @Test
        fun `(성공) 두 번 같은 글자가 연속되는 답안 단어에서, 오늘의 단어와 두 글자 '부분 일치 글자'일 때 비교한다`() {
            val answerWord = Word("llama")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.PRESENT,
                    LetterMatch.PRESENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                ).toWordResult(),
            )
        }

        @Test
        fun `(성공) 두 번 같은 글자가 연속되는 답안 단어에서, '부분 일치 글자'와 '완전 일치 글자'를 가질 때 비교한다`() {
            val answerWord = Word("hello")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.PRESENT,
                    LetterMatch.CORRECT,
                    LetterMatch.ABSENT,
                ).toWordResult(),
            )
        }

        @Test
        fun `(성공) 같은 글자가 연속 하지 않는 답안 단어에서, '부분 일치 글자'와 '완전 일치 글자'를 가질 때 비교한다`() {
            val answerWord = Word("label")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.PRESENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.CORRECT,
                ).toWordResult(),
            )
        }

        @Test
        fun `(성공) 세 개의 같은 글자를 가지는 답안 단어에서 하나의 글자 결과만 '부분 일치 글자'일 때 비교한다`() {
            val answerWord = Word("puppy")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.PRESENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                ).toWordResult(),
            )
        }

        @Test
        fun `(성공) 네 개의 글자 결과가 '완전 일치 글자'일 때 비교한다`() {
            val answerWord = Word("spell")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(
                mutableListOf(
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                    LetterMatch.ABSENT,
                    LetterMatch.CORRECT,
                    LetterMatch.CORRECT,
                ).toWordResult(),
            )
        }
    }

    @Nested
    inner class `단어 결과 중 글자 일치 상태가 모두 같은 경우` {
        @Test
        fun `(성공) 모든 글자 결과가 '불일치 상태'일 때 비교한다`() {
            val answerWord = Word("major")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(MutableList(WORD_LENGTH) { LetterMatch.ABSENT }.toWordResult())
        }

        @Test
        fun `(성공) 모든 글자 결과가 '부분 일치 글자'일 때 비교한다`() {
            val answerWord = Word("illps")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(MutableList(WORD_LENGTH) { LetterMatch.PRESENT }.toWordResult())
        }

        @Test
        fun `(성공) 모든 글자 결과가 '완전 일치 글자'일 때 비교한다`() {
            val answerWord = Word("spill")

            val result = wordleGameLogic.compare(answerWord)

            assertThat(result).isEqualTo(MutableList(WORD_LENGTH) { LetterMatch.CORRECT }.toWordResult())
        }
    }
}
