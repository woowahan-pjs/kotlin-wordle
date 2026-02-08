package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import wordle.translation.WordBookExtractor
import java.time.LocalDate

class AnswerGeneratorTest {
    val fileName: String = "testWord.txt"
    val wordExtractor = WordBookExtractor.extract(fileName)

    @Test
    @DisplayName("오늘의 단어를 반환합니다.")
    fun test01() {
        // arrange
        val answerGenerator = AnswerGenerator(wordExtractor)
        val targetDate: LocalDate = LocalDate.of(2021, 6, 19)
        val expected = Word("zinzo")

        // act
        val sut: Word = answerGenerator.generateAnswer(targetDate)

        // assert
        assertThat(sut).isEqualTo(expected)
    }
}
