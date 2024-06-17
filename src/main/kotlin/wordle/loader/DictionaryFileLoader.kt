package wordle.loader

import java.io.File

private const val CARRIAGE_RETURN = "\n"
private const val WORDS_FILE_PATH = "src/main/resources/words.txt"
private val dictionaryWords: List<String> by lazy { load() }
val dictionaryWordsSize: Int get() = dictionaryWords.size

fun isContainsDictionaryWord(word: String): Boolean = dictionaryWords.contains(word)

fun indexOfDictionaryWord(index: Int): String = dictionaryWords[index]

private fun load(): List<String> =
    File(WORDS_FILE_PATH).readText()
        .split(CARRIAGE_RETURN)
