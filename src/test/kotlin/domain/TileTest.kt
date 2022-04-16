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
            .withMessage(Tile.ERROR_ALLOWED_CHARACTER_MSG)
    }

    @Test
    fun `두개의 Tile이 서로 일치하면 참을 반환한다`() {
        // given
        val tile = Tile('a')
        val anotherTile = Tile('a')

        // then
        assertThat(tile).isEqualTo(anotherTile)
    }

    @Test
    fun `두개의 Tile이 서로 다르면 거짓을 반환한다`() {
        // given
        val tile = Tile('a')
        val anotherTile = Tile('b')

        // then
        assertThat(tile).isNotEqualTo(anotherTile)
    }
}
