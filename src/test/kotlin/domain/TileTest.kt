package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TileTest {
    @Test
    fun `타일은 하나의 문자를 가지고 있다`() {
        // given
        val tile = Tile('쿄')

        // then
        assertThat(tile.character).isEqualTo('쿄')
    }
}