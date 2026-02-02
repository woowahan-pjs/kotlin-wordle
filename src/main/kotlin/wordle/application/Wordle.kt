package wordle.application

import wordle.domain.result.Results
import wordle.domain.word.Answer
import wordle.domain.word.Word

class Wordle(
    private val answer: Answer,
) {
    fun round(word: Word): Results {
        return answer.verify(word)
    }
}
