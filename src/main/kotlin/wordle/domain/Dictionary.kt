package wordle.domain

interface Dictionary {
    val words: List<String>

    fun hasWord(word: Word): Boolean {
        return words.contains(word.value)
    }

    fun findAnswer(answerSelector: AnswerSelector): String {
        return words[answerSelector.findIndex(words.size)]
    }
}
