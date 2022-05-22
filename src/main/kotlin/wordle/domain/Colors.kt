package wordle.domain

class Colors(val values: MutableList<Color>) {

    companion object {
        private const val SIZE = 5

        fun createEmpty(): Colors {
            return Colors(mutableListOf(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY))
        }
    }

    init {
        require(values.size == SIZE) { "색깔의 개수가 %d개가 아닙니다".format(SIZE) }
    }

    fun isCorrect(): Boolean {
        return values.all { it == Color.GREEN }
    }

    fun paint(color: Color, index: Int) {
        if (values[index] == Color.GRAY) {
            values[index] = color
        }
    }
}
