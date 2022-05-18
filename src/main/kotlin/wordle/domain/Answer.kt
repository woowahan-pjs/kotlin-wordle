package wordle.domain

import wordle.domain.Mark.*

class Answer(private val answer: String) {

    init {
        require(answer.length == WORD_SIZE) { "[ERROR] 부적절한 글자 길이입니다." }
        require(answer.isInWords()) { "[ERROR] 목록에 존재하지 않는 단어입니다." }
    }

    fun compareToWord(word: String): MutableList<Mark> {
        val result = MutableList(WORD_SIZE) { NONE }
        val wordTable = createWordTable(word)
        matchExact(word, result, wordTable)
        matchExist(result, wordTable)
        return result
    }

    private fun createWordTable(word: String): HashMap<Char, Int> {
        val wordTable = HashMap<Char, Int>()
        for (char in word) {
            wordTable[char] = wordTable.getOrDefault(char, 0) + 1
        }
        return wordTable
    }

    private fun matchExact(word: String, result: MutableList<Mark>, wordTable: HashMap<Char, Int>) {
        for (i in 0 until WORD_SIZE) {
            markExact(i, word, result, wordTable)
        }
    }

    private fun markExact(i: Int, word: String, result: MutableList<Mark>, wordTable: HashMap<Char, Int>) {
        if (word[i] == answer[i]) {
            result[i] = EXACT
            wordTable.computeIfPresent(word[i]) { _, v -> v - 1 }
        }
    }

    private fun matchExist(result: MutableList<Mark>, wordTable: HashMap<Char, Int>) {
        for (index in 0 until WORD_SIZE) {
            markExist(index, result, wordTable)
        }
    }

    private fun markExist(index: Int, result: MutableList<Mark>, wordTable: HashMap<Char, Int>) {
        if (isExist(index, result, wordTable, answer[index])) {
            result[index] = EXIST
            wordTable.computeIfPresent(answer[index]) { _, v -> v - 1 }
        }
    }

    private fun isExist(
        index: Int,
        result: MutableList<Mark>,
        wordTable: HashMap<Char, Int>,
        charOfAnswer: Char,
    ) = result[index] == NONE && wordTable.containsKey(charOfAnswer) && wordTable[charOfAnswer] != 0
}
