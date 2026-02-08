package wordle.domain

/**
 * Wordle 게임 한 판을 의미합니다.
 */
class Wordle(
    private val validator: WordleWordValidator,
    private val answer: Word,
) {
    /**
     * 입력된 단어의 유효성과 정답을 판단합니다.
     *
     * @param input 입력된 단어
     * @return 정답
     */
    fun round(input: String): Results {
        val word: Word = WordFactory.create(validator, input)

        return WordResolver(answer).check(word)
    }
}
