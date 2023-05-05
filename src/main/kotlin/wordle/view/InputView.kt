package wordle.view

import wordle.domain.Word

class InputView {

    fun readGuessWord(): Word {
        println("정답을 입력해 주세요.")
        return Word(readln().trim())
    }
}
