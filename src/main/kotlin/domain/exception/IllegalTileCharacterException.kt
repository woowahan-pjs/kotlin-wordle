package domain.exception

class IllegalTileCharacterException(message: String) : RuntimeException(message)

fun checkTileCharacter(value: Boolean, lazyMessage: () -> String) {
    if (!value) {
        val message = lazyMessage()
        throw IllegalTileCharacterException(message)
    }
}
