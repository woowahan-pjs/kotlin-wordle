package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordResultTest {
    @Test
    fun `단어 결과는 글자 일치 상태 목록 없이 생성할 수 있다 - 모두 '불일치 상태'를 가진다`() {
        val wordResult = WordResult()

        assertThat(wordResult).isEqualTo(MutableList(WORD_LENGTH) { LetterMatch.ABSENT }.toWordResult())
    }

    @Test
    fun `답안 단어의 일치 여부를 변경한다`() {
        val wordResult = WordResult()

        wordResult.changeMatchType(0, LetterMatch.CORRECT)
        wordResult.changeMatchType(2, LetterMatch.PRESENT)
        wordResult.changeMatchType(4, LetterMatch.CORRECT)

        assertThat(wordResult).isEqualTo(
            mutableListOf(
                LetterMatch.CORRECT,
                LetterMatch.ABSENT,
                LetterMatch.PRESENT,
                LetterMatch.ABSENT,
                LetterMatch.CORRECT,
            ).toWordResult(),
        )
    }
}

fun MutableList<LetterMatch>.toWordResult(): WordResult = WordResult(this)
