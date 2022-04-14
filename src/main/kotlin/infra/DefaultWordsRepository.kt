package infra

import domain.Tiles
import domain.WordsRepository
import java.time.LocalDate

class DefaultWordsRepository : WordsRepository {
    private val words: Set<Tiles>
    private val today: Tiles

    init {
        val now = LocalDate.now()
        val standard = LocalDate.of(2021, 6, 19)

        val values: List<String> = this.javaClass.classLoader.getResourceAsStream("words.txt").bufferedReader().readLines()

        val daysFromStandard = (now.toEpochDay() - standard.toEpochDay()).toInt()

        this.words = values.map { Tiles.of(it) }.toSet()
        this.today = Tiles.of(values[daysFromStandard % this.words.size])
    }

    override fun exists(tiles: Tiles): Boolean {
        return words.contains(tiles)
    }

    override fun getTodayWords(): Tiles {
        return today
    }
}
