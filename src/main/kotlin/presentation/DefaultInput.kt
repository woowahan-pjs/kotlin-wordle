package presentation

import domain.Input
import domain.Tiles

class DefaultInput : Input {
    override fun read(): Tiles {
        var text = ""

        do {
            println("정답을 입력해 주세요.")

            text = System.`in`.bufferedReader().readLine()

        } while (text.length != 5)

        return Tiles.of(text)
    }
}