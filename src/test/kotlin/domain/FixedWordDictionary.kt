package domain

class FixedWordDictionary(val word: String) : WordDictionary {

    override fun pickWord(): String {
        return word
    }

    override fun contains(target: String): Boolean {
        return true
    }
}
