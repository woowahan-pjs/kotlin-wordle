package wordle.domain

object WordRules {
    const val MAX_LENGTH: Int = 5
    val WORD_REGEX: Regex = Regex("^[a-zA-Z]*$")
}
