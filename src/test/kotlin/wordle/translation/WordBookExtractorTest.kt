package wordle.translation

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import wordle.domain.WordBook

class WordBookExtractorTest {
    @Test
    @DisplayName("파일을 읽어 단어장으로 반환합니다.")
    fun test01() {
        // arrange
        val fileName: String = "testWord.txt"

        // act
        val sut: WordBook = WordBookExtractor.extract(fileName)

        // assert
        Assertions.assertThat(sut).isNotNull
    }
}
