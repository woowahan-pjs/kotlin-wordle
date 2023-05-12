package domain

interface WordDictionary {

    fun pickWord(): String

    fun contains(target: String): Boolean
}
