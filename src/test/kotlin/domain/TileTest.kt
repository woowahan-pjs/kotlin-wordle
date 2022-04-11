package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class TileTest {
    @Test
    fun `타일은 하나의 문자를 가지고 있다`() {
        // given
        val tile = Tile('a')

        // then
        assertThat(tile.character).isEqualTo('a')
    }

    @ParameterizedTest
    @ValueSource(chars = [' ', '0', '1', '5', '9', 'ㄱ', '⬜'])
    fun `타일은 알파벳만 입력이 가능하다`(character: Char) {
        assertThatIllegalArgumentException()
            .isThrownBy { Tile(character) }
            .withMessage("문자는 알파벳만 입력이 가능합니다.")
    }
}