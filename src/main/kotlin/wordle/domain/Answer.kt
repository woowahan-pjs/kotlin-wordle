package wordle.domain

private const val WORD_SIZE = 5

class Answer(private val answer: String) {

    init {
        require(answer.length == WORD_SIZE) { "[ERROR] 부적절한 글자 길이입니다." }
        require(Words.contains(answer)) { "[ERROR] 목록에 존재하지 않는 단어입니다." }
    }

    fun match(word: String): MutableList<Mark> {
        val wordTable = createWordTable(word)
        val result = MutableList(WORD_SIZE) { Mark.NONE }
        matchExact(word, result, wordTable)
        matchExist(result, wordTable)
        return result
    }

    private fun createWordTable(word: String): HashMap<Char, Int> {
        val wordTable = HashMap<Char, Int>()
        for (it in word) {
            wordTable[it] = wordTable.getOrDefault(it, 0) + 1
        }
        return wordTable
    }

    private fun matchExact(
        word: String,
        result: MutableList<Mark>,
        wordTable: HashMap<Char, Int>,
    ) {
        for (i in 0 until WORD_SIZE) {
            markExact(word, i, result, wordTable)
        }
    }

    private fun markExact(
        word: String,
        i: Int,
        result: MutableList<Mark>,
        wordTable: HashMap<Char, Int>,
    ) {
        val key = word[i]
        if (key == answer[i]) {
            result[i] = Mark.EXACT
            wordTable.computeIfPresent(key) { _, v -> v - 1 }
        }
    }

    private fun matchExist(
        result: MutableList<Mark>,
        wordTable: HashMap<Char, Int>,
    ) {
        for (i in 0 until WORD_SIZE) {
            markExist(i, result, wordTable)
        }
    }

    private fun markExist(
        i: Int,
        result: MutableList<Mark>,
        wordTable: HashMap<Char, Int>,
    ) {
        val key = answer[i]
        if (isExist(result, i, wordTable, key)) {
            result[i] = Mark.EXIST
            wordTable.computeIfPresent(key) { _, v -> v - 1 }
        }
    }

    private fun isExist(
        result: MutableList<Mark>,
        i: Int,
        wordTable: HashMap<Char, Int>,
        key: Char,
    ) = result[i] == Mark.NONE && wordTable.containsKey(key) && wordTable[key] != 0
}
