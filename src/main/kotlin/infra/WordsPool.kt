package infra

import domain.Tiles
import domain.Words
import java.io.File
import java.time.LocalDate

class WordsPool : Words {

    private val today: Tiles

    init {
        val nowEpochDay = LocalDate.now().toEpochDay()
        val daysFromStandard = (nowEpochDay - BASE_TIME_EPOCH_DAY).toInt()
        this.today = WORDS[daysFromStandard % WORDS.size]
    }

    override fun exists(tiles: Tiles): Boolean {
        return WORDS.contains(tiles)
    }

    override fun getTodayWords(): Tiles {
        return today
    }

    companion object {
        private val BASE_TIME_EPOCH_DAY = LocalDate.of(2021, 6, 19).toEpochDay()
        private val WORDS: List<Tiles> =
            File(ClassLoader.getSystemResource("words.txt").file).bufferedReader().readLines().map { Tiles.of(it) }
    }
}
