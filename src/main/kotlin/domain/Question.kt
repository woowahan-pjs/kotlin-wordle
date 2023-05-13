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
        return questionWord == word
    }

    fun getHint(word: String): List<Hint> {
        validateWord(word)
        val hint = MutableList(questionWord.length) { Hint.GRAY }.toMutableList()

        val slicedQuestion: MutableList<Char> = questionWord.toMutableList()
        val slicedWord: MutableList<Char> = word.toMutableList()

        checkGreen(slicedWord, slicedQuestion, hint)
        checkYellow(slicedWord, slicedQuestion, hint)

        return hint
    }

    private fun checkYellow(
        slicedWord: MutableList<Char>,
        slicedQuestion: MutableList<Char>,
        hint: MutableList<Hint>,
    ) {
        slicedWord.forEachIndexed { index, word ->
            if (word != BLANK && slicedQuestion.contains(word)) {
                hint[index] = Hint.YELLOW
                slicedQuestion[slicedQuestion.indexOf(word)] = BLANK
            }
        }
    }

    private fun checkGreen(
        slicedWord: MutableList<Char>,
        slicedQuestion: MutableList<Char>,
        hint: MutableList<Hint>,
    ) {
        slicedWord.forEachIndexed { index, _ ->
            if (slicedQuestion[index] == slicedWord[index]) {
                hint[index] = Hint.GREEN
                slicedQuestion[index] = BLANK
                slicedWord[index] = BLANK
            }
        }
    }

    companion object {
        private const val NO_SUCH_WORD_MESSAGE = "word.txt 내의 단어를 선택해주세요"
        private const val BLANK = ' '
    }
}
