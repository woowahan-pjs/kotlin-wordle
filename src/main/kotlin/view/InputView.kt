package view

class InputView {

    fun readAnswer(): String {
        println("정답을 입력해 주세요.")
        return readlnOrNull() ?: throw IllegalArgumentException("정답을 입력해 주세요.")
    }
}
