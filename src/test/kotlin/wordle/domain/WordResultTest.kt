package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WordResultTest {
    @Test
    fun `(성공) 단어 결과는 글자 일치 상태 목록 없이 생성할 수 있다 - 모두 '불일치 상태'를 가진다`() {
        val wordResult = WordResult()

        assertThat(wordResult).isEqualTo(MutableList(WORD_LENGTH) { LetterMatch.ABSENT }.toWordResult())
    }

    @Test
    fun `(성공) 해당 글자의 글자 일치 상태를 변경할 수 있다`() {
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

    @Test
    fun `(성공) 해당 글자가 '완전 일치 상태'인지 확인한다`() {
        val wordResult = WordResult()

        wordResult.changeMatchType(1, LetterMatch.CORRECT)

        assertAll(
            { assertThat(wordResult.isCorrectMatchIndex(0)).isFalse() },
            { assertThat(wordResult.isCorrectMatchIndex(1)).isTrue() },
        )
    }

    @Test
    fun `(예외) 단어 결과 목록이 단어 길이와 일치헤야 한다`() {
        assertThatThrownBy { WordResult(mutableListOf()) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("단어 결과의 글자 일치 상태 목록들은 단어 길이인 ${WORD_LENGTH}와 일치해야 합니다.")
    }
}

fun MutableList<LetterMatch>.toWordResult(): WordResult = WordResult(this)
