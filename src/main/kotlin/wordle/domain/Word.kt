package wordle.domain

class Word(val word: String) {

    init {
        require(word.length == 5) { "단어는 5글자여야 합니다." }
        require(isAlphabet(word)) { "단어는 알파벳으로만 구성해야 합니다." }
        require(WordPool.words.contains(word)) { "허용되지 않는 단어입니다." }
    }

    private fun isAlphabet(word: String): Boolean {
        return word.matches("^[a-zA-Z]*$".toRegex())
    }

    companion object {
        fun from(word: String) = Word(word.lowercase())
    }
}
