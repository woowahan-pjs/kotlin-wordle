package wordle.domain

class Game(private val word: String) {
    val fixedCount = 6
    val results: Results = Results()
    var isPlaying: Boolean = true
        private set

    fun playRound(answer: Answer) {
        val result = answer.compareToWord(word)
        results.add(result)
        isPlaying = !isOver(result)
    }

    private fun isOver(result: MutableList<Mark>) =
        results.isLimit() || result.all { it == Mark.EXACT }

    fun findTryCount(): Int {
        return results.value.size
    }
}
