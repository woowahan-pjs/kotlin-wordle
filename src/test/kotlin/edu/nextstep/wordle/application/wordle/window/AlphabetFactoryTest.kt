package edu.nextstep.wordle.application.wordle.window

import edu.nextstep.wordle.application.wordle.window.Alphabet
import edu.nextstep.wordle.application.wordle.window.AlphabetFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AlphabetFactoryTest {

    @Test
    fun `팩토리를 통해 알파벳을 찾을 수 있다`() {
        //given
        val alphabetFactory = AlphabetFactory.create()

        //when
        val alphabet = alphabetFactory.findBy("a")

        //then
        assertThat(alphabet).isEqualTo(Alphabet("a"))
    }

}
