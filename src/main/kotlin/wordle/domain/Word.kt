package wordle.domain

class Word private constructor(val word: List<Letter>) {

    init {
        require(word.size == 5) { "단어는 5글자여야 합니다." }
    }

    companion object {
        fun from(word: String): Word {
            require(WordPool.words.contains(word)) { "허용되지 않는 단어입니다." }
            return Word(word.mapIndexed { index, letter -> Letter(index, letter) })
        }
    }
}
