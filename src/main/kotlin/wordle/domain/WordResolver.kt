package wordle.domain

/**
 * 입력 값이 정답과 일치하는지 판단합니다.
 */
class WordResolver(
    private val answer: Word,
) {
    private val counter: MutableMap<Char, Int> = init(answer)

    /**
     * 입력된 [input]가 정답과 동일한지 판단합니다.
     *
     * @param input 입력한 단어
     * @return 정답
     */
    fun check(input: Word): Results {
        val result = MutableList(answer.length()) { Result.ABSENT }
        val answerArray = answer.toCharArray()
        val charArray = input.toCharArray()

        markCorrect(charArray, answerArray, result)
        markPresent(charArray, answerArray, result)

        return Results(result)
    }

    /**
     * 입력 단어[inputArray]와 정답 단어[answerArray]를 인덱스 기준으로 비교하여
     * 동일한 위치에 동일한 문자가 있는 경우 `CORRECT`로 판정합니다.
     *
     * CORRECT로 판정된 문자는
     * - 결과를 CORRECT로 설정하고
     * - 정답 배열에서 제거하여 이후 판단에서 제외하며
     * - [counter]에서 해당 문자의 남은 수를 차감합니다.
     *
     * @param inputArray 사용자가 입력한 단어의 문자 배열
     * @param answerArray 정답 단어의 문자 배열
     * @param result 각 인덱스별 판정 결과를 저장하는 리스트
     */
    private fun markCorrect(
        inputArray: CharArray,
        answerArray: CharArray,
        result: MutableList<Result>,
    ) {
        for (i in inputArray.indices) {
            val ch = inputArray[i]
            if (isCorrectAt(i, ch)) {
                result[i] = Result.CORRECT
                answerArray[i] = BLANK_CHAR
                decreaseCounter(ch)
            }
        }
    }

    /**
     * 입력 단어[inputArray]의 각 문자에 대해
     * 정답 단어[answerArray]에 동일한 문자가 존재하지만 인덱스가 다른 경우 `PRESENT`로 판정합니다.
     *
     * PRESENT로 판정되는 조건은 다음과 같습니다.
     * - 해당 인덱스가 이미 CORRECT로 판정되지 않았을 것
     * - 정답 단어에 해당 문자가 남아 있을 것
     *
     * PRESENT로 판정된 문자는,
     * - 결과를 PRESENT로 설정하고
     * - [counter]에서 해당 문자의 남은 수를 차감합니다.
     *
     * @param inputArray 사용자가 입력한 단어의 문자 배열
     * @param answerArray 정답 단어의 문자 배열
     * @param result 각 인덱스별 판정 결과를 저장하는 리스트
     */
    private fun markPresent(
        inputArray: CharArray,
        answerArray: CharArray,
        result: MutableList<Result>,
    ) {
        for (i in inputArray.indices) {
            // 이미 정답 처리된 인덱스는 스킵
            if (result[i] == Result.CORRECT) {
                continue
            }

            val ch = inputArray[i]
            if (!canMarkPresent(ch)) {
                continue
            }

            result[i] = Result.PRESENT
            decreaseCounter(ch)
        }
    }

    /**
     * 주어진 인덱스[index]에서 입력 문자[char]가
     * 정답 단어의 문자와 정확히 일치하는지 판단합니다.
     *
     * 이 메서드는 문자 값과 인덱스 위치가 모두 같은 경우에만 `true`를 반환합니다.
     *
     * @param index 비교할 문자 위치
     * @param char 사용자가 입력한 문자
     * @return 해당 인덱스에서 문자가 정확히 일치하면 true, 그렇지 않으면 false
     */
    private fun isCorrectAt(
        index: Int,
        char: Char,
    ): Boolean = answer.check(index, char)

    /**
     * 주어진 문자[char]가 PRESENT로 판정될 수 있는지 판단합니다.
     *
     * PRESENT로 판정되기 위한 조건은 다음과 같습니다.
     * - 정답 단어에 해당 문자가 존재할 것
     * - [counter]에 해당 문자의 남은 수가 1 이상일 것
     *
     * 이 메서드는 문자 위치와는 상관없이
     * 남은 문자 수[counter] 기준으로만 판단합니다.
     *
     * @param char 사용자가 입력한 문자
     * @return PRESENT로 판정 가능하면 true, 그렇지 않으면 false
     */
    private fun canMarkPresent(char: Char): Boolean {
        if (!counter.containsKey(char)) {
            return false
        }

        if (counter.getValue(char) <= 0) {
            return false
        }

        return true
    }

    /**
     * [counter]에서 주어진 문자[char]의 남은 수를 감소시킵니다.
     *
     * 이 메서드는 CORRECT 또는 PRESENT로 판정된 문자를
     * 이후 중복 판정에서 제외하기 위해 사용됩니다.
     *
     * @param char 남은 수를 차감할 문자
     */
    private fun decreaseCounter(char: Char) {
        counter[char] = counter.getValue(char) - 1
    }

    companion object {
        const val BLANK_CHAR: Char = '_'

        /**
         * 정답 단어의 char와 빈도수를 map으로 초기화합니다.
         *
         * @param answer 오늘의 단어 (정답)
         * @return 오늘의 단어의 char, 빈도수의 map
         */
        private fun init(answer: Word): MutableMap<Char, Int> = answer.charCountMap()
    }
}
