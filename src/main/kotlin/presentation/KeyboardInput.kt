package presentation

import domain.Input
import domain.Tiles

class KeyboardInput: Input {
    override fun read(): Tiles {
        val readLine = System.`in`.bufferedReader().readLine()

        return Tiles.of(readLine)
    }
}