package wordle.domain

/**
 * Word 리스트를 가진 단어장 객체입니다.
 */
@JvmInline
value class WordBook(
    private val values: List<Word>,
) {
    /**
     * 단어장의 총 단어 갯수를 반환합니다.
     *
     * @return 단어장의 총 단어 갯수
     */
    fun getAllCount(): Int = values.size

    /**
     * 특정 index의 단어를 가져옵니다.
     *
     * @param index 대상 index
     * @return index에 해당하는 [Word] 반환
     * @throws IllegalArgumentException index가 단어장의 사이즈 범위에 벗어나는 경우
     */
    @Throws(IllegalAccessException::class)
    fun getWord(index: Int): Word {
        require(index in 0 until getAllCount()) {
            "index가 범위를 벗어났습니다. :$index"
        }

        return values[index]
    }

    /**
     * [word]가 단어장에 존재하는지 여부를 반환합니다.
     *
     * @param word 단어장에 존재하는지 확인하고 싶은 단어
     * @return 입력된 [word]가 단어장에 존재하는지 여부
     */
    fun exists(word: String): Boolean = values.contains(Word(word))

    companion object {
        /**
         * 입력 받은 문자열 리스트를 단어장으로 생성하여 반환합니다.
         *
         * @param words 단어장으로 만들고자하는 단어 리스트
         * @return 입력된 리스트로 생성된 단어장
         */
        fun from(
            validator: WordValidator,
            words: List<String>,
        ): WordBook = WordBook(words.map { WordFactory.create(validator, it) })
    }
}
