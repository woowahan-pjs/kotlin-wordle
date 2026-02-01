package view

import domain.Constant
import domain.GameString

class InputView {

    fun getInput(): GameString {
        println("${Constant.WORD_LENGTH}글자 단어를 입력해 주세요");
        while(true) {
            val inputWord = readlnOrNull()?.trim() ?: ""
            try {
                return GameString(inputWord)
            } catch (exception: Exception) {
                println(exception.message)
                continue
            }
        }
    }
}