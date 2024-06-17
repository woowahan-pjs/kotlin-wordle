package wordle.infra

import java.io.File

private const val CARRIAGE_RETURN = "\n"
private const val WORDS_FILE_PATH = "src/main/resources/words.txt"
private val dictionaryWords: List<String> by lazy { loadDictionaryWords() }
val dictionaryWordSet: Set<String> by lazy { dictionaryWords.toSet() }

private fun loadDictionaryWords(): List<String> =
    File(WORDS_FILE_PATH).readText()
        .split(CARRIAGE_RETURN)
