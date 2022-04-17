package wordle.domain

class Words(private val words: List<Word>) {

    fun findAnswer(position: Int): Word {
        return words[(position % words.size)]
    }

    fun contains(value: String): Boolean {
        return words.contains(Word(value))
    }
}
