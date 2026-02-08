package wordle.domain

/**
 * 단어를 의미하는 값 객체
 */
@JvmInline
value class Word(
    private val value: String,
) {
    /**
     * 입력 된 index의 char가 입력된 char와 같은지 확인합니다.
     *
     * @param index 대상 index
     * @param char 비교를 위한 char
     * @return 동일한지 여부
     */
    fun check(
        index: Int,
        char: Char,
    ): Boolean {
        val charArray = value.toCharArray()

        require(charArray.size > index) { "범위를 벗어났습니다." }

        return charArray[index] == char
    }

    /**
     * 단어의 길이를 반환합니다.
     *
     * @return 단어의 길이
     */
    fun length(): Int = value.length

    /**
     * 단어를 char 배열로 만들어 반환합니다.
     *
     * @return 단어의 캐릭터 배열
     */
    fun toCharArray(): CharArray = value.toCharArray()

    /**
     * 단어에서 사용된 char의 갯수를 구한 MutableMap을 반환합니다.
     *
     * @return 사용된 char와 갯수의 MutableMap
     */
    fun charCountMap(): MutableMap<Char, Int> =
        value
            .groupBy { it }
            .mapValues { (_, v) -> v.size }
            .toMutableMap()
}
