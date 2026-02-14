package wordle.domain

class Words(private val words: List<Word>) {

    fun findAnswer(position: Position): Word {
        return words[position.percent(words.size)]
    }

    fun contains(value: String): Boolean {
        return words.contains(Word(value))
    }
}
