package domain.exception

class IllegalTileSizeException(message: String) : RuntimeException(message)

fun checkTileSize(value: Boolean, lazyMessage: () -> String) {
    if (!value) {
        val message = lazyMessage()
        throw IllegalTileSizeException(message)
    }
}
