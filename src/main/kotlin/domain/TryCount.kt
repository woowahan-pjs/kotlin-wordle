package domain

data class TryCount(val tryCount: Int = 0) {

    init {
        validatePositive()
    }

    private fun validatePositive() {
        require(tryCount >= 0) { ERROR_MESSAGE }
    }

    fun plus(): TryCount {
        return TryCount(tryCount.inc())
    }

    fun isSame(other: TryCount): Boolean {
        return this.tryCount == other.tryCount
    }

    companion object {
        private const val ERROR_MESSAGE = "양수를 입력해주세요"
    }
}
