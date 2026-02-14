package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileTest {

    @Test
    fun `타일은 노란색, 초록색, 회색을 가진다`() {
        assertThat(Tile.values()).containsExactly(Tile.YELLOW, Tile.GREEN, Tile.GRAY)
    }
}
