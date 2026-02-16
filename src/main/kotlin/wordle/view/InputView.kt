package wordle.view

import wordle.domain.Words

object InputView {

    fun askWord(words: Words): String {
        while (true) {
            val input = question()
            if (words.contains(input)) {
                return input
            }

            println("올바르지 않은 단어입니다.")
        }
    }

    private fun question(): String {
        println("정답을 입력해 주세요.")
        return readln()
    }
}
