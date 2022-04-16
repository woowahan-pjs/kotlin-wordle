package domain

data class MatchResults(val results: List<MatchResult>) {
    init {
        require(results.size == REQUIRE_RESULTS_SIZE) { ERROR_RESULTS_SIZE_MSG }
    }

    fun isCorrect(): Boolean = results.all { it == MatchResult.CORRECT }

    companion object {
        const val ERROR_RESULTS_SIZE_MSG = "결과는 5개로 구성되어야 합니다."
        const val REQUIRE_RESULTS_SIZE = 5
    }
}
