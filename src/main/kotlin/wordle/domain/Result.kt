package wordle.domain

data class Result(val matchResults: List<MatchResult>) {

    fun isRight(): Boolean {
        return matchResults.all { it == MatchResult.GREEN }
    }
}
