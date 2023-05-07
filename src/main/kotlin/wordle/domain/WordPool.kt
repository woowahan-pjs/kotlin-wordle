package wordle.domain

import java.io.File

object WordPool {
    val words = readWordsFile("src/main/resources/words.txt")
    fun readWordsFile(filePath: String): List<String> {
        val file = File(filePath)
        println(file.absolutePath)
        return file.readLines()
    }
}
