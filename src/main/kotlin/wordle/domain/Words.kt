package wordle.domain

import java.time.LocalDate

class Words(private val values: List<Word>, today: LocalDate = LocalDate.now()) {

    private val answer: Word = findAnswer(today)

    private fun findAnswer(date: LocalDate): Word {
        val days = date.compareTo(STANDARD_DATE)
        return values[days % values.size]
    }

    fun contains(word: Word): Boolean = values.contains(word)

    fun isCorrect(word: Word): Boolean {
        return word == answer
    }

    fun check(word: Word): List<Tile> {
        return greenOrGray(word).yellowOrExist(word)
    }

    private fun greenOrGray(word: Word): List<Tile> {
        return word.value.mapIndexed { index, letter ->
            if (answer.value[index] == letter) {
                Tile.GREEN
            } else {
                Tile.GRAY
            }
        }
    }

    private fun List<Tile>.yellowOrExist(word: Word): List<Tile> {
        var notCheckedLetters = answer.value.filterIndexed { index, _ -> get(index) != Tile.GREEN }

        return mapIndexed { index, tile ->
            val letter = word.value[index]
            if (letter in notCheckedLetters && tile != Tile.GREEN) {
                notCheckedLetters = notCheckedLetters.removeFirst(letter)
                Tile.YELLOW
            } else {
                tile
            }
        }
    }

    private fun String.removeFirst(letter: Char): String {
        return replaceFirst(letter.toString(), "")
    }

    companion object {
        const val WORD_SIZE: Int = 5
        private val STANDARD_DATE: LocalDate = LocalDate.of(2021, 6, 19)
    }
}
