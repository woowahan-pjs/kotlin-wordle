package wordle.domain

import java.time.LocalDate

/**
 * 오늘의 단어를 추출하기 위한 Index를 구하는 값 객체 입니다.
 *
 * Index는 `((현재 날짜 - 2021년 6월 19일) % 배열의 크기)` 와 같은 식으로 계산됩니다.
 */
@JvmInline
value class AnswerIndex(
    val value: Int,
) {
    companion object {
        const val MIN_INDEX: Int = 0

        val BASE_DATE: LocalDate = LocalDate.of(2021, 6, 19)

        /**
         * 입력된 배열 사이즈와 날짜에 대응하는 index 값을 구합니다.
         *
         * 구해진 index가 [MIN_INDEX]보다 작은 경우,
         * [MIN_INDEX]값으로 반환됩니다.
         *
         * @param date index를 구하고자 하는 대상 날짜
         * @param arraySize index를 구하고자 하는 대상의 사이즈
         */
        fun fromDate(
            date: LocalDate,
            arraySize: Int,
        ): AnswerIndex {
            val result = calculate(date, arraySize)

            if (result < MIN_INDEX) {
                return AnswerIndex(MIN_INDEX)
            }

            return AnswerIndex(result)
        }

        /**
         * 주어진 계산식으로 index 값을 구합니다.
         */
        private fun calculate(
            date: LocalDate,
            arraySize: Int,
        ): Int = ((date.toEpochDay() - BASE_DATE.toEpochDay()) % arraySize).toInt()
    }
}
