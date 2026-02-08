package wordle.domain

import wordle.translation.ResultsConverter

/**
 * Wordle 게임의 총 결과를 저장하는 객체
 */
class WordleResults(
    private val value: MutableList<Results> = mutableListOf(),
) {
    /**
     * Wordle 한 판의 결과를 저장합니다.
     *
     * @param results wordle 한 판의 결과
     */
    fun add(results: Results) {
        value.add(results)
    }

    /**
     * Wordle 게임의 총 결과를 tile 형식으로 출력합니다.
     *
     * @return tile 형식의 wordle 게임 총 결과
     */
    fun display(): String {
        val stringBuilder: StringBuilder = StringBuilder()

        value.forEach { stringBuilder.append(ResultsConverter.convert(it)).append("\n") }

        return stringBuilder.toString().trim()
    }
}
