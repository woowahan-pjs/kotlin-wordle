package domain

@JvmInline
value class Tile(val character: Char) {
    init {
        require(character in 'A'..'z') { "문자는 알파벳만 입력이 가능합니다." }
    }
}