package presentation

import domain.Input
import domain.Tiles

object DefaultInput : Input {
    private const val INPUT_MESSAGE = "정답을 입력해 주세요."

    override tailrec fun read(): Tiles {
        val tiles = nextTiles()

        return tiles ?: read()
    }

    private fun nextTiles(): Tiles? {
        try {
            println(INPUT_MESSAGE)

            val text = System.`in`.bufferedReader().readLine()

            return Tiles.of(text)
        } catch (e: Exception) {
            println(e.message)
        }

        return null
    }
}
