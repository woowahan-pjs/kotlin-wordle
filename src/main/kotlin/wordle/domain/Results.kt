package wordle.domain

class Results(results: List<Result> = emptyList()) {
    private val value = results.toMutableList()

    val results: List<Result>
        get() = value.toList()

    fun add(result: Result) {
        value.add(result)
    }
}
