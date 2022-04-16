package domain.exception

class IllegalMatchResultsSizeException(message: String) : RuntimeException(message)

fun checkMatchResultsSize(value: Boolean, lazyMessage: () -> String) {
    if (!value) {
        val message = lazyMessage()
        throw IllegalMatchResultsSizeException(message)
    }
}
