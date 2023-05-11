package domain

data class TryCount(val tryCount: Int = 0) {

    init {
        validatePositive()
    }

    private fun validatePositive() {
        require(tryCount >= 0) { ERROR_MESSAGE }
    }

    fun plus():TryCount {
        return TryCount(tryCount.inc())
    }

    companion object {
        private const val ERROR_MESSAGE ="양수를 입력해주세요"
    }
}
