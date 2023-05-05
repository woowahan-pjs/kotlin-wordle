package wordle.domain

class WordleWords(
    private val words: List<Word>
) {

    val size: Int
        get() = words.size

    fun getWord(index: Int): Word = words[index]

    fun contains(word: Word): Boolean = words.contains(word)
}
