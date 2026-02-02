package wordle.domain.word

import java.time.LocalDate

data class WordIndex(
    val value: Int,
) {
    companion object {
        const val MIN_INDEX: Int = 0
        val BASE_DATE: LocalDate = LocalDate.of(2021, 6, 19)

        fun create(
            date: LocalDate,
            arraySize: Int,
        ): WordIndex {
            val result = calculate(date, arraySize)

            if (result < MIN_INDEX) {
                return WordIndex(MIN_INDEX)
            }

            return WordIndex(result)
        }

        private fun calculate(
            date: LocalDate,
            arraySize: Int,
        ): Int {
            return ((date.toEpochDay() - BASE_DATE.toEpochDay()) % arraySize).toInt()
        }
    }
}
