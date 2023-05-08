package wordle.domain

data class Result(private val matchResults: List<MatchResult>) {

    fun isRight(): Boolean {
        return matchResults.all { it == MatchResult.GREEN }
    }
}
