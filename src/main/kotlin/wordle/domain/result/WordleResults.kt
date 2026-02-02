package wordle.domain.result

class WordleResults(
    private val value: MutableList<Results> = mutableListOf(),
) {
    fun add(results: Results) {
        value.add(results)
    }

    fun display(): String = value.joinToString("\n") { it.convert() }
}
