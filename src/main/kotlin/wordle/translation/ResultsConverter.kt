package wordle.translation

import wordle.domain.Result
import wordle.domain.Results

/**
 * Results를 Tile 형식 변환 처리기
 */
object ResultsConverter {
    /**
     * [results]를 타일 형식으로 변환합니다.
     *
     * @param results 정답 묶음
     * @return tile 형식 문자열
     */
    fun convert(results: Results): String {
        val builder = StringBuilder()

        for (result in results) {
            builder.append(
                when (result) {
                    Result.ABSENT -> "⬜"
                    Result.PRESENT -> "🟨"
                    Result.CORRECT -> "🟩"
                },
            )
        }

        return builder.toString()
    }
}
