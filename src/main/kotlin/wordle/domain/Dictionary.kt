package wordle.domain

import wordle.infra.contains

fun isDictionaryWord(word: String): Boolean = contains(word)
