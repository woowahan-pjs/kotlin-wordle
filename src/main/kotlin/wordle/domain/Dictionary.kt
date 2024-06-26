package wordle.domain

import wordle.infra.contains
import wordle.infra.dictionaryWord

fun isDictionaryWord(word: String): Boolean = contains(word)

fun dictionaryElementAt(index: Int): String = dictionaryWord(index)
