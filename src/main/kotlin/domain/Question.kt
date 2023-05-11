package domain

class Question(private val wordDictionary: WordDictionary) {

    private val questionWord: String = wordDictionary.pickWord()

    fun isAnswer(word: String): Boolean {
        validateWord(word)
        return checkAnswer(word)
    }

    private fun validateWord(word: String) {
        require(wordDictionary.contains(word)) { word + NO_SUCH_WORD_MESSAGE }
    }

    private fun checkAnswer(word: String): Boolean {
        return questionWord.equals(word)
    }

    companion object {
        private const val NO_SUCH_WORD_MESSAGE = "word.txt 내의 단어를 선택해주세요"
    }
}
