package wordle.view

import wordle.domain.Mark.EXACT
import wordle.domain.Mark.NONE
import wordle.domain.Mark.EXIST
import wordle.domain.Results
import wordle.domain.Game
import wordle.domain.Mark

fun printStartMessage() {
    println("WORDLE 을 6번 만에 맞춰 보세요.\n시도의 결과는 타일의 색 변화로 나타납니다.")
}

private fun printInputMessage() {
    println("정답을 입력해 주세요.")
}

fun inputAnswer(): String {
    printInputMessage()
    return readln()
}

fun printResults(game: Game) {
    if (!game.isPlaying) {
        printTryCount(game.findTryCount())
    }
    printTiles(game.results)
}

fun printTryCount(tryCount: Int) {
    println()
    println("$tryCount/6")
}

fun printTiles(results: Results) {
    println()
    results.getReadOnlyResults().forEach {
        printTile(it)
    }
    println()
}

private fun printTile(result: List<Mark>) {
    StringBuilder().apply {
        result.forEach {
            when (it) {
                NONE -> append("⬜")
                EXIST -> append("\uD83D\uDFE8")
                EXACT -> append("\uD83D\uDFE9")
            }
        }
        println(toString())
    }
}
