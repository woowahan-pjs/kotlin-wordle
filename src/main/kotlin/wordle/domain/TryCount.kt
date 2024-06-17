package wordle.domain

import wordle.exception.ExceptionMessage

class TryCount(private var count: Int = MAX_TRY_COUNT) {
    val attempts get() = MAX_TRY_COUNT - count

    fun isRemainder(): Boolean = count > 0

    fun minus() {
        check(isRemainder()) { ExceptionMessage.TRY_COUNT_HAS_NOT_REMAINDER.message }
        --count
    }
}

const val MAX_TRY_COUNT = 6
