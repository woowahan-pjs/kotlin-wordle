package wordle.domain

data class Letter(private val position: Int, private val letter: Char) {

    init {
        require(letter in 'a'..'z') { "글자는 소문자 알파벳만 허용합니다." }
        require(position in 0..4) { "단어는 5글자여야 합니다." }
    }

    fun match(other: Letter): MatchResult = when {
        this == other -> MatchResult.GREEN
        this.letter == other.letter -> MatchResult.YELLOW
        else -> MatchResult.GRAY
    }
}
