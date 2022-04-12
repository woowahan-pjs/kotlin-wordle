package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TileTest {

    @Test
    fun `타일은 노란색, 초록색, 회색을 가진다`() {
        assertThat(Tile.values()).containsExactly(Tile.YELLOW, Tile.GREEN, Tile.GRAY)
    }

    @ParameterizedTest
    @CsvSource("GRAY, \uD83D\uDFE8", "GREEN, \uD83D\uDFE9", "YELLOW, \uD83D\uDFE7")
    fun `흰색 타일은 ⬜, 초록색 타일은 🟩, 노란색 타일은 🟨 값을 가진다`(tile: Tile, color: String) {
        assertThat(tile.value).isEqualTo(color)
    }
}
