package edu.nextstep.wordle.config

import edu.nextstep.wordle.application.wordle.Word
import edu.nextstep.wordle.application.wordle.dictionary.MemoryWordFinder
import edu.nextstep.wordle.application.wordle.dictionary.MemoryWordProvider
import edu.nextstep.wordle.application.wordle.dictionary.WordFinder
import edu.nextstep.wordle.application.wordle.dictionary.WordProvider
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

class WordleConfig {

    fun memoryWordFinder(): WordFinder = MemoryWordFinder(words)

    fun memoryWordProvider(): WordProvider = MemoryWordProvider(words)

    companion object {
        private const val BASE_DIRECTORY = ""
        private const val CLASS_PATH = "src/main/resources"
        private const val DICTIONARY_FILE_NAME = "words.txt"
        private val words: List<Word> =
            Files.newInputStream(Paths.get(BASE_DIRECTORY, CLASS_PATH, DICTIONARY_FILE_NAME))
                .bufferedReader()
                .lines()
                .map { Word.create(it) }
                .toList()
    }
}
