package wordle.domain.result

data class Results(
    val values: List<Result>,
) {
    fun convert(): String = values.joinToString("") {
        when (it) {
            Result.ABSENT -> "⬜"
            Result.PRESENT -> "🟨"
            Result.CORRECT -> "🟩"
        }
    }

    fun isAnswer(): Boolean {
        return values.all { it == Result.CORRECT }
    }
}
