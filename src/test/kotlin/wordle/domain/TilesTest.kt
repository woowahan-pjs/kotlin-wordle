package wordle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TilesTest {
    @Test
    fun `모두 초록색인 경우 우승자이다`() {
        val givenTiles = Tiles(listOf(Tile.GREEN, Tile.GREEN, Tile.GREEN, Tile.GREEN, Tile.GREEN))

        val actual = givenTiles.isWinner()

        assertThat(actual).isTrue
    }

    @Test
    fun `모두 초록색이 아닌 경우 우승자가아니다`() {
        val givenTiles = Tiles(listOf(Tile.GREEN, Tile.YELLOW, Tile.GREEN, Tile.GRAY, Tile.GREEN))

        val actual = givenTiles.isWinner()

        assertThat(actual).isFalse
    }

}
