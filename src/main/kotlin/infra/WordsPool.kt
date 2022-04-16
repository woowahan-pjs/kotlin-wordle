package infra

import domain.Tiles
import domain.WordsRepository
import java.time.LocalDate

class WordsPool : WordsRepository {
    private val words: Set<Tiles>
    private val today: Tiles

    init {
        val nowEpochDay = LocalDate.now().toEpochDay()

        val values: List<String> = object {}.javaClass.getResourceAsStream("words.txt").bufferedReader().readLines()

        val daysFromStandard = (nowEpochDay - BASE_TIME_EPOCH_DAY).toInt()

        this.words = values.map { Tiles.of(it) }.toSet()
        this.today = Tiles.of(values[daysFromStandard % this.words.size])
    }

    override fun exists(tiles: Tiles): Boolean {
        return words.contains(tiles)
    }

    override fun getTodayWords(): Tiles {
        return today
    }

    companion object {
        private val BASE_TIME_EPOCH_DAY = LocalDate.of(2021, 6, 19).toEpochDay()
    }
}
