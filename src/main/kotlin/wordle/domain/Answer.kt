package wordle.domain

private const val RANGE_START = 0
private const val RANGE_END = 4

class Answer(private val word: Word) {

    fun compare(word: Word): Colors {
        val colors = Colors.createEmpty()
        val paint = createPainter(colors, mutableListOf())
        paint({ index: Int, consumed: MutableList<Int> -> isExactMatch(word, index, consumed) }, Color.GREEN)
        paint({ index: Int, consumed: MutableList<Int> -> isAnyMatch(word, index, consumed) }, Color.YELLOW)
        return colors
    }

    private fun createPainter(
        colors: Colors,
        consumedIndices: MutableList<Int>,
    ): ((Int, MutableList<Int>) -> Boolean, Color) -> Unit {
        return fun(predicate: (Int, MutableList<Int>) -> Boolean, color: Color) {
            for (index in (RANGE_START..RANGE_END).filter { predicate.invoke(it, consumedIndices) }) {
                colors.paint(color, index)
            }
        }
    }

    private fun isExactMatch(word: Word, index: Int, consumedIndices: MutableList<Int>): Boolean =
        this.word.compareByIndex(word, index) && consumeIfMatched(consumedIndices, index)

    private fun isAnyMatch(word: Word, outerIndex: Int, consumedIndices: MutableList<Int>): Boolean =
        (RANGE_START..RANGE_END).any {
            this.word.compareByIndex(word, it, outerIndex) && consumeIfMatched(consumedIndices, it)
        }

    private fun consumeIfMatched(consumedIndices: MutableList<Int>, index: Int): Boolean {
        if (!consumedIndices.contains(index)) {
            consumedIndices.add(index)
            return true
        }
        return false
    }
}
