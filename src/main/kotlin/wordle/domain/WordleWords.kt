package wordle.domain

class WordleWords(
    private val words: List<Word>
) {

    fun getWord(index: Int): Word = words[index]

    fun contains(word: Word): Boolean = words.contains(word)
}
