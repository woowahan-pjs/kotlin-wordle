package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val 오늘의_단어 = Word("hello")

class WordComparatorTest {
    @Test
    fun `단어의 모든 글자가 '불일치'하다`() {
        val answerWord = Word("adapt")

        val actual = WordComparator(오늘의_단어).matchCorrect(answerWord).matchPresent(answerWord)

        assertThat(actual.result()).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                ),
            ),
        )
    }

    @Test
    fun `단어에 '완전 일치'한 글자가 있다`() {
        val answerWord = Word("harry")

        val actual = WordComparator(오늘의_단어).matchCorrect(answerWord).matchPresent(answerWord)

        assertThat(actual.result()).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.CORRECT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                ),
            ),
        )
    }

    @Test
    fun `단어에 '부분 일치'한 글자가 있다`() {
        val answerWord = Word("shake")

        val actual = WordComparator(오늘의_단어).matchCorrect(answerWord).matchPresent(answerWord)

        assertThat(actual.result()).isEqualTo(
            WordResult(
                mutableListOf(
                    LetterMatch.ABSENT,
                    LetterMatch.PRESENT,
                    LetterMatch.ABSENT,
                    LetterMatch.ABSENT,
                    LetterMatch.PRESENT,
                ),
            ),
        )
    }
}
