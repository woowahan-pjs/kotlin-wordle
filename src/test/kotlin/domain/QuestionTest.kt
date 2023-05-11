package domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
class QuestionTest{

    @Test
    fun 타깃파일에_없는_단어를_줄_시_예외가_발생한다(){
        // given
        val word = "abcde"
        val question = Question(TodayWordDictionary())

        // expect
        assertThrows(IllegalArgumentException::class.java) { question.isAnswer(word) }
    }

    @Test
    fun 정답이라면_true를_반환한다(){
        // given
        val word = "abcde"
        val question = Question(FixedWordDictionary(word))

        // expect
        assertTrue(question.isAnswer(word))
    }

    @Test
    fun 정답이_아니_라면_false를_반환한다(){
        // given
        val word = "cigar"
        val question = Question(FixedWordDictionary(word))

        // expect
        assertFalse(question.isAnswer("sissy"))
    }
}
