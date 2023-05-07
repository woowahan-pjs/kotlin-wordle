package wordle.domain

private const val RANGE_START = 0
private const val RANGE_END = 4

class Answer(private val word: Word) {
    fun compare(word: Word): List<Color> {
        val exactIndices = compareExact(word)
        val copyOfExactIndices: MutableList<Int> = mutableListOf()
        copyOfExactIndices.addAll(exactIndices)
        val anyIndices = compareAny(word, copyOfExactIndices)
        return merge(exactIndices, anyIndices)
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

    private fun compareExact(word: Word): List<Int> {
        return (RANGE_START..RANGE_END).filter { this.word.compareByIndex(word, it) }
    }

    private fun compareAny(word: Word, exactIndices: MutableList<Int>): List<Int> {
        val consumedLetterIndex: MutableList<Int> = exactIndices
        return (RANGE_START..RANGE_END).filter { isAnyMatch(word, it, consumedLetterIndex) }
    }

    private fun isAnyMatch(word: Word, outerIndex: Int, consumedLetterIndex: MutableList<Int>): Boolean {
        val anyMatchIndex = (RANGE_START..RANGE_END)
            .findLast { this.word.compareByIndex(word, it, outerIndex, consumedLetterIndex) } ?: return false
        consumedLetterIndex.add(anyMatchIndex)
        return true
    }

    fun getAnswer(): String {
        return word.word
    }
}
