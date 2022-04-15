package wordle.view

import wordle.domain.Word

object InputView {

    fun askWord(words: List<Word>): String {
        while (true) {
            val input = question()
            if (words.contains(Word(input))) {
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
