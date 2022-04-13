package domain

class Game(
    private val input: Input
) {
    fun start() {
        (BEGIN_INDEX..MAX_TRY_COUNT).forEach { index ->
            val tiles = input.read()
        }
    }

    companion object {
        const val BEGIN_INDEX = 1
        const val MAX_TRY_COUNT = 6
    }
}
