package wordle.domain

class Game(private val word: String, val results: Results) {

    var isPlaying: Boolean = true
        private set

    fun playRound(answer: Answer) {
        val result = answer.match(word)
        results.add(result)
        if (isOver(result)) {
            isPlaying = false
        }
    }

    private fun isOver(result: MutableList<Mark>) =
        results.isLimit() || result.all { mark -> mark == Mark.EXACT }

    fun findTryCount(): Int {
        return results.value.size
    }
}
