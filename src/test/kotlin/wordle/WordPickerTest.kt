package wordle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import wordle.domain.WordPicker
import java.io.File

class WordPickerTest {

    @Test
    fun 단어를_추출한다() {
        // given
        val wordPicker = WordPicker()

        // when
        val actual = wordPicker.pick()

        // then
        assertThat(actual).isNotEmpty
    }

    @Test
    fun 추출한_단어는_words_텍스트_파일에_존재한다() {
        // given
        val wordPicker = WordPicker()
        val words = File(ClassLoader.getSystemResource("words.txt").file).readLines()

        // when
        val word = wordPicker.pick()

        // then
        assertThat(word).isIn(words)
    }
}
