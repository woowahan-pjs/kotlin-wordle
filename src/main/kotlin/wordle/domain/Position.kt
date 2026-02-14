package wordle.domain

class Position(private val position: Int) {

    fun percent(value: Int): Int {
        return position % value
    }
}
