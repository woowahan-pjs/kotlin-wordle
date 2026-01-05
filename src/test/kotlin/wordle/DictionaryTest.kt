package wordle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import wordle.domain.Dictionary
import wordle.domain.Word

class DictionaryTest {

    class MockDictionary(override val words: List<String>) : Dictionary

    private val dictionary = MockDictionary(listOf("world", "angry", "hello"))
    private val answerSelector = { _: Int -> 1 }

    @ParameterizedTest
    @ValueSource(strings = ["asder", "wrfdx", "bljwq"])
    fun `단어가 존재하지 않으면 false`(word: String) {
        //when
        val result = dictionary.hasWord(Word(word))

        //then
        assertThat(result).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["hello", "world", "angry"])
    fun `단어가 존재하면 true`(word: String) {
        //when
        val result = dictionary.hasWord(Word(word))

        //then
        assertThat(result).isTrue()
    }

    @Test
    fun `answerSelector 기준으로 단어를 추출한다`() {
        val findTodayWord = dictionary.findAnswer(answerSelector)

        assertThat(findTodayWord).isEqualTo("angry")
    }
}
