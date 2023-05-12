package wordle.domain

@JvmInline
value class UserGuess(val value: String) {

    init {
        require((value.length) == REQUIRED_LENGTH)
    }

    companion object {
        private const val REQUIRED_LENGTH = 5
    }
}
