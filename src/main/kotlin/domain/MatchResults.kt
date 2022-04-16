package domain

import domain.exception.checkMatchResultsSize

data class MatchResults(val results: List<MatchResult>) {
    init {
        checkMatchResultsSize(results.size == REQUIRE_RESULTS_SIZE) { ERROR_RESULTS_SIZE_MSG }
    }

    fun isAllGreens(): Boolean = results.all { it == MatchResult.GREEN }

    companion object {
        private const val ERROR_RESULTS_SIZE_MSG = "결과는 5개로 구성되어야 합니다."
        private const val REQUIRE_RESULTS_SIZE = 5
    }
}
