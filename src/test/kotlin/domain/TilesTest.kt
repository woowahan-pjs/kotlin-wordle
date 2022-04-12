package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class TilesTest {
    @Test
    fun `Tiles는 5개의 Tile로 구성이 되어있다`() {
        // given
        val elements = listOf(Tile('a'), Tile('b'), Tile('c'), Tile('d'), Tile('e'))

        // when
        val tiles = Tiles(elements)

        // then
        assertThat(tiles.tiles).containsExactlyElementsOf(elements)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 6, 10])
    fun `Tiles는 5개의 Tile로 구성이 안되면 실패한다`(size: Int) {
        // given
        val elements: List<Tile> = (0 until size).map { Tile('a') }

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { Tiles(elements) }
            .withMessage("타일은 5개로 구성되어야 합니다.")
    }

    @Test
    fun `Tiles는 문자열 5글자로 구성 할 수 있다`() {
        val tiles = Tiles.of("hello")

        assertThat(tiles.tiles)
            .containsExactly(Tile('h'), Tile('e'), Tile('l'), Tile('l'), Tile('o'))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "h", "hell", "hellow"])
    fun `Tiles는 문자열 5글자로 구성이 안되면 실패한다`(words: String) {
        assertThatIllegalArgumentException()
            .isThrownBy { Tiles.of(words) }
            .withMessage("타일은 5개로 구성되어야 합니다.")
    }
}
