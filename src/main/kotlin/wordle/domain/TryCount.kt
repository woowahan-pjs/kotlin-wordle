package wordle.domain

@JvmInline
value class TryCount(private val value: Int) {
    init {
        require(value in MIN_VALUE until MAX_VALUE) {
            INPUT_NOT_ALLOWED_MESSAGE
        }
    }

    fun increase(): TryCount {
        return TryCount(value + 1)
    }

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_VALUE = 6
        private const val INPUT_NOT_ALLOWED_MESSAGE = "더 이상 입력할 수 없습니다."
    }
}
