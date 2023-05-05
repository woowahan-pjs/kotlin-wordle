package wordle.domain

class WordleCalculator {
    fun calculate(answer: String, input: String): List<ResultType> {
        val result = mutableListOf<ResultType>()

        answer.forEachIndexed { index, word ->
            if (word == input[index]) {
                result.add(ResultType.GREEN)
            } else {
                result.add(ResultType.GRAY)
            }
        }

        return result
    }
}
