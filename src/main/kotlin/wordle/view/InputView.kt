package wordle.view

import wordle.domain.Word

object InputView {

    fun askWord(words: List<Word>): String {
        while (true) {
            val input = question("정답을 입력해 주세요.")
            if (words.contains(Word(input))) {
                return input
            }

            println("올바르지 않은 단어입니다.")
        }
    }

    private tailrec fun question(question: String): String {
        println(question)
        return readLine() ?: question(question)
    }
}
