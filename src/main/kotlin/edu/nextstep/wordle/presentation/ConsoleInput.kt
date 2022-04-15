package edu.nextstep.wordle.presentation

import edu.nextstep.wordle.application.wordle.Word

fun inputWord(): Word {
    println("정답을 입력해 주세요.")
    return readLine()?.toWord() ?: throw IllegalArgumentException("단어를 입력해주세요")
}

private fun String?.toWord(): Word {
    if (this == null || this.isEmpty()) {
        return inputWord()
    }

    return try {
        Word.create(this)
    } catch (e: IllegalArgumentException) {
        println(e.message)
        inputWord()
    }
}
