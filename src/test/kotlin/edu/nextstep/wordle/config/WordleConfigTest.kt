package edu.nextstep.wordle.config

import edu.nextstep.wordle.application.wordle.Word
import edu.nextstep.wordle.application.wordle.dictionary.MemoryWordFinder
import edu.nextstep.wordle.application.wordle.dictionary.MemoryWordProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class WordleConfigTest {
    @Test
    fun `classpath 에 있는 words-txt 를 읽어온다`() {
        //given
        val finder = WordleConfig().memoryWordFinder() as MemoryWordFinder

        //when
        val result = finder.words.size

        //then
        assertThat(result).isEqualTo(2309)
    }

    @Test
    fun `(현재 날짜 - 2021년 6월 19일) % 배열의 크기) 번째의 단어를 제공한다`() {
        //given
        val provider = MemoryWordProvider(listOf(
            Word.create("abcde"),
            Word.create("abcda"),
            Word.create("abcdb"),
            Word.create("abcdd"),
        ))

        //when
        val word1 = provider.provide(LocalDate.of(2021, 6, 19))
        val word2 = provider.provide(LocalDate.of(2021, 6, 20))
        val word3 = provider.provide(LocalDate.of(2021, 6, 21))
        val word4 = provider.provide(LocalDate.of(2021, 6, 22))

        //then
        assertThat(word1).isEqualTo(Word.create("abcde"))
        assertThat(word2).isEqualTo(Word.create("abcda"))
        assertThat(word3).isEqualTo(Word.create("abcdb"))
        assertThat(word4).isEqualTo(Word.create("abcdd"))
    }
}
