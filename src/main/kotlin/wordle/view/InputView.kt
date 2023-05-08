package wordle.view

object InputView {
    fun readWord(): String {
        println("정답을 입력해 주세요.")
        return readln()
    }
}
