package wordle.domain

import wordle.infra.dictionaryWordSet

fun isDictionaryWord(word: String): Boolean = dictionaryWordSet.contains(word)
