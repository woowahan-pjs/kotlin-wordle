package wordle.infrastructure

object Scanner {
    fun input(): String {
        val input = readlnOrNull()?.trim() ?: ""
        if (input == "") {
            throw IllegalArgumentException("입력값을 확인해주세요.")
        }
        return input.lowercase()
    }
}
