package wordle.infra

import java.nio.charset.StandardCharsets

private const val NEW_LINE = "\n"
private const val WORDS_FILE_PATH = "words.txt"
private val classLoader: ClassLoader = Thread.currentThread().contextClassLoader
private val dictionaryWords: List<String> by lazy { loadDictionaryWords() }
val dictionaryWordSet: Set<String> by lazy { dictionaryWords.toSet() }

fun contains(word: String): Boolean = dictionaryWordSet.contains(word)

private fun loadDictionaryWords(): List<String> =
    classLoader.getResourceAsStream(WORDS_FILE_PATH)
        ?.bufferedReader(StandardCharsets.UTF_8)
        ?.use { it.readText().split(NEW_LINE) }
        ?.filter { it.isNotBlank() }
        ?: throw IllegalArgumentException("Dictionary File not found: $WORDS_FILE_PATH")
