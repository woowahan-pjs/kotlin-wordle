package wordle.domain

import java.io.File

class Answers {
    val answer: Word
        get() = createAnswer()

    private fun createAnswer() = WORDS.findAnswer()

    companion object {
        // 선언 위치 확인 필요
        private const val ANSWERS_TEXT_PATH = "./words.txt"
        val WORDS = createWords()

        private fun createWords(): Words {
            val wordsFile = getResourceText().readLines()
            return Words(wordsFile.map { Word(it) })
        }

        // 파일 읽는 부분만 목킹이 되면 될 듯
        private fun getResourceText(): File {
            return File(ClassLoader.getSystemResource(ANSWERS_TEXT_PATH).file)
        }
    }
}
