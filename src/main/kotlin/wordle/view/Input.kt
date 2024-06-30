package wordle.view

import wordle.domain.Word

object Input {

    fun guess(): Word {
        println("정답을 입력해 주세요.")
        return Word(readln().trim())
    }

}
