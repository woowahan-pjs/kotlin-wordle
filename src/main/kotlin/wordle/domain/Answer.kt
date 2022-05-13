package wordle.domain

private const val RANGE_START = 0
private const val RANGE_END = 4

class Answer(val word: Word) {

    fun compare(word: Word): List<Color> {
        val exactIndices = compareExact(word)
        val anyIndices = compareAny(word)
        return merge(exactIndices, anyIndices)
    }

    private fun compareExact(word: Word): List<Int> {
        return (RANGE_START..RANGE_END).filter { this.word.compareByIndex(word, it) }
    }

    private fun compareAny(word: Word): List<Int> {
        return (RANGE_START..RANGE_END).filter { isAnyMatch(word, it) }
    }

    private fun merge(greenIndices: List<Int>, yellowIndices: List<Int>): List<Color> {
        return (RANGE_START..RANGE_END).map { defineColor(it, greenIndices, yellowIndices) }
    }

    private fun defineColor(index: Int, greenIndices: List<Int>, yellowIndices: List<Int>): Color {
        if (greenIndices.contains(index)) {
            return Color.GREEN
        }
        if (yellowIndices.contains(index)) {
            return Color.YELLOW
        }
        return Color.GRAY
    }

    private fun isAnyMatch(word: Word, outerIndex: Int): Boolean {
        return (RANGE_START..RANGE_END).any { this.word.compareByIndex(word, it, outerIndex) }
    }
}
