package infra

import domain.Tiles
import domain.WordsPool
import java.io.File
import java.time.LocalDate

class DefaultWordsPool : WordsPool {
    private val words: Set<Tiles>
    private val todayWords: Tiles

    init {
        val values: List<String> = WORDS_FILE.readLines()

        val daysFromStandard = (LocalDate.now().toEpochDay() - BASE_DATE.toEpochDay()).toInt()

        this.words = values.map(::Tiles).toSet()
        this.todayWords = Tiles(values[daysFromStandard % this.words.size])
    }

    override fun contains(tiles: Tiles): Boolean {
        return words.contains(tiles)
    }

    override fun getTodayWords(): Tiles {
        return todayWords
    }

    companion object {
        private val BASE_DATE = LocalDate.of(2021, 6, 19)
        private val WORDS_FILE = File(ClassLoader.getSystemResource("words.txt").file)
    }
}
