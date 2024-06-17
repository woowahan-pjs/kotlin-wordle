package wordle.domain

import wordle.exception.WordleExceptionCode.TRY_COUNT_HAS_NOT_REMAINDER

data class TryCount(private var count: Int = MAX_TRY_COUNT) {
    val attempts get() = MAX_TRY_COUNT - count

    fun isRemainder(): Boolean = count > 0

    fun minus() {
        check(isRemainder()) { TRY_COUNT_HAS_NOT_REMAINDER.message }
        this.count -= 1
    }
}

const val MAX_TRY_COUNT = 6
