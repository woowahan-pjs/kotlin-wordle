package domain

import domain.Hint.GRAY
import domain.Hint.GREEN
import domain.Hint.YELLOW
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
class QuestionTest {

    @Test
    fun 타깃파일에_없는_단어를_줄_시_예외가_발생한다() {
        // given
        val word = "abcde"
        val question = Question(TodayWordDictionary())

        // expect
        assertThrows(IllegalArgumentException::class.java) { question.isAnswer(word) }
    }

    @Test
    fun 정답이라면_true를_반환한다() {
        // given
        val word = "abcde"
        val question = Question(FixedWordDictionary(word))

        // expect
        assertTrue(question.isAnswer(word))
    }

    @Test
    fun 정답이_아니_라면_false를_반환한다() {
        // given
        val word = "cigar"
        val question = Question(FixedWordDictionary(word))

        // expect
        assertFalse(question.isAnswer("sissy"))
    }

    @Test
    fun 같은_자리에_같은_글자이면_해당_자리에_GREEN_힌트를_준다() {
        // given
        val word = "cigar"
        val question = Question(FixedWordDictionary(word))

        // when
        val hint: List<Hint> = question.getHint("cbdef")

        // then
        assertThat(hint).containsExactly(GREEN, GRAY, GRAY, GRAY, GRAY)
    }

    @Test
    fun 다른_자리에_같은_글자이면_입력한_해당_자리에_YELLOW_힌트를_준다() {
        // given
        val word = "cigar"
        val question = Question(FixedWordDictionary(word))

        // when
        val hint: List<Hint> = question.getHint("bgbgi")

        // then
        assertThat(hint).containsExactly(GRAY, YELLOW, GRAY, GRAY, YELLOW)
    }

    @Test
    fun GREEN과_YELLOW가_섞인_경우() {
        // given
        val word = "spill"
        val question = Question(FixedWordDictionary(word))

        // when
        val hint: List<Hint> = question.getHint("hello")

        // then
        assertThat(hint).containsExactly(GRAY, GRAY, YELLOW, GREEN, GRAY)
    }
}
