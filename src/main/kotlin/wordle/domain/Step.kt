package wordle.domain

/**
 * 사용자가 [Word]를 입력하여 정답과 비교한 결과를 가진 한 번의 단계
 */
data class Step(val answer: String, val word: Word) {

    enum class Result {
        CORRECT,
        MISMATCH,
        WRONG;
    }

    val result: List<Result>

    init {
        result = if (answer == word.value) {
            List(5) { Result.CORRECT }.toList()
        } else {
            MutableList(5) { Result.WRONG }.apply {
                fillCorrect(this)
                fillMismatch(this)
            }
        }
    }

    val isCorrect = result.all { it == Result.CORRECT }

    private fun fillCorrect(initResult: MutableList<Result>) {
        word.value.forEachIndexed { index, letter ->
            if (answer[index] == letter) {
                initResult[index] = Result.CORRECT
            }
        }
    }

    private fun fillMismatch(correctedResult: MutableList<Result>) {
        val calculatedAnswer =
            StringBuilder(answer.filterIndexed { index, _ -> correctedResult[index] != Result.CORRECT })

        word.value.forEachIndexed { index, letter ->
            if (correctedResult[index] != Result.CORRECT && calculatedAnswer.contains(letter)) {
                correctedResult[index] = Result.MISMATCH
                val foundIndex = calculatedAnswer.indexOf(letter)
                calculatedAnswer.deleteAt(foundIndex)
            }
        }
    }
}
