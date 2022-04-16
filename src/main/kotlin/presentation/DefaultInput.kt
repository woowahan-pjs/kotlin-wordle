package presentation

import domain.Input
import domain.Tiles

object DefaultInput : Input {
    private const val INPUT_MESSAGE = "정답을 입력해 주세요."

    override fun read(): Tiles {
        println(INPUT_MESSAGE)
        val text = readln()
        return Tiles.of(text)
    }
}
