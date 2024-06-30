package wordle.domain

fun interface AnswerSelector {
    fun findIndex(maxSize: Int): Int
}