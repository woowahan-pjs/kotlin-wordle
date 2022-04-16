package presentation

import domain.Tiles

tailrec fun read(): Tiles {
    val tiles = nextTiles()

    return tiles ?: read()
}

private fun nextTiles(): Tiles? {
    try {
        println(INPUT_MESSAGE)

        val text = System.`in`.bufferedReader().readLine()

        return Tiles(text)
    } catch (e: Exception) {
        println(e.message)
    }

    return null
}

private const val INPUT_MESSAGE = "정답을 입력해 주세요."
