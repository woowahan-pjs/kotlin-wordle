package wordle.domain

import java.io.File

object WordPool {
    private const val filePath = "src/main/resources/words.txt"
    val words = readWordsFile()

    private fun readWordsFile(): List<String> {
        val file = File(filePath)
        return file.readLines()
    }
}
