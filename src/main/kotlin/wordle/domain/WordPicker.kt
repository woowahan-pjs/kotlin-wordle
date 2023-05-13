package wordle.domain

import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar

class WordPicker {

    fun pick(): String {
        val words = getWords()
        val index = getIndexOfAnswer(words)
        return words[index]
    }

    private fun getIndexOfAnswer(words: List<String>): Int {
        val today = Calendar.getInstance()
        val criteria = SimpleDateFormat("yyyy-mm-dd").parse(CRITERIA_DATE)

        val result = (today.time.time - criteria.time) / (60 * 60 * 24 * 1000)

        return result.toInt() % words.size
    }

    private fun getWords(): List<String> {
        val file = ClassLoader.getSystemResource(FILE_NAME).file
        return File(file).readLines()
    }

    companion object {
        private const val CRITERIA_DATE = "2021-06-19"
        private const val FILE_NAME = "words.txt"
    }
}
