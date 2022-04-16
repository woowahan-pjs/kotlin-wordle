package domain

import domain.exception.checkTileCharacter

@JvmInline
value class Tile(val character: Char) {
    init {
        checkTileCharacter(character in ALLOWED_CHARACTER) { ERROR_ALLOWED_CHARACTER_MSG }
    }

    companion object {
        private const val ERROR_ALLOWED_CHARACTER_MSG = "문자는 알파벳만 입력이 가능합니다."
        private val ALLOWED_CHARACTER = 'a'..'z'
    }
}
