package wordle.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WordTest {

    @ParameterizedTest
    @ValueSource(strings = ["hell", "helloo"])
    fun `단어는 5글자가 아니면 예외`(string: String) {
        //when & then
        assertThrows<IllegalArgumentException> { Word.from(string) }
    }

    @Test
    fun `단어가 알파벳이 아니면 예외`() {
        //when & then
        assertThrows<IllegalArgumentException> { Word.from("가나다라마") }
    }

    @Test
    fun `words에 존재하지 않는 단어면 예외`() {
        //when & then
        assertThrows<IllegalArgumentException> { Word.from("kkkkk") }
    }
}
