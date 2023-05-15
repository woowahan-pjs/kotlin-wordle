package wordle.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class AnswerGeneratorTest {

    @Test
    fun `words 파일의 특정 위치의 단어를 가져온다`() {
        // given
        val generator: AnswerGenerator = AnswerGenerator(WordPool.words)

        // when
        val word: Word = generator.generate(LocalDate.of(2021, 6, 20))

        // then
        assertEquals(word, Word.from(WordPool.words[1]))
    }
}
