package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import wordle.infra.dictionaryWordsSize

private const val CONTAINS_WORD = "hello"

class DictionaryKtTest {
    @Test
    fun `(성공) 사전에 포함된 문자열인지 확인할 수 있다`() {
        val notInWord = "abced"

        assertAll(
            { assertThat(isDictionaryWord(CONTAINS_WORD)).isTrue() },
            { assertThat(isDictionaryWord(notInWord)).isFalse() },
        )
    }

    @Test
    fun `(성공) 사전 안의 단어목록 중 인덱스에 해당하는 문자열을 가져온다`() {
        val index = 0

        assertThat(dictionaryElementAt(index)).isEqualTo(CONTAINS_WORD)
    }

    @Test
    fun `(성공) 단어 목록 인덱스의 최댓값보다 큰 인덱스를 입력하면 단어 목록의 인덱스는 다시 0부터 시작한다`() {
        val index = dictionaryWordsSize

        assertThat(dictionaryElementAt(index)).isEqualTo(CONTAINS_WORD)
    }
}
