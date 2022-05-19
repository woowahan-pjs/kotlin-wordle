package wordle.domain

class Colors(val values: List<Color>) {

    companion object {
        private const val SIZE = 5
    }

    init {
        require(values.size == SIZE) { "색깔의 개수가 %d개가 아닙니다".format(SIZE) }
    }

    fun isCorrect(): Boolean {
        return values.all { it == Color.GREEN }
    }
}