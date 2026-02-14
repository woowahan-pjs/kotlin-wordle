package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WordsCreatorTest {

    @Test
    fun `WordsCreator에_저장된_단어들을_생성한다`() {
        val creator = MockingCreator()

        val createWords = creator.createWords()

        assertAll(
            { assertThat(createWords.contains("chunk")).isTrue },
            { assertThat(createWords.contains("queen")).isTrue },
            { assertThat(createWords.contains("awake")).isTrue }
        )
    }
}
