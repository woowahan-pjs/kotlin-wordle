package wordle.view

object InputView {

    fun requestAnswer(): String {
        println()
        println("정답을 입력해 주세요.")
        return readln()
    }
}