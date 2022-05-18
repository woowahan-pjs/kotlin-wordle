package wordle.domain

class Game(private val word: String) {

    val results: Results = Results()
    var isPlaying: Boolean = true
        private set

    fun playRound(answer: Answer) {
        val result = answer.compareToWord(word)
        results.add(result)
        if (isOver(result)) {
            isPlaying = false
        }
    }

    private fun isOver(result: List<Mark>) =
        results.isLimit() || result.all { it == Mark.EXACT }

    fun findTryCount() =
        results.findTryCount()
}
