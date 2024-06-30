package wordle.infra

import wordle.domain.Dictionary
import java.io.File

class FileDictionary: Dictionary {
    private val PATH = "./src/main/resources"
    private val FILE_NAME = "words.txt"

    override val words: List<String> = File(PATH, FILE_NAME).readLines()
}