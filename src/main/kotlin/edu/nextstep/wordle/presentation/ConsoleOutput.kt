package edu.nextstep.wordle.presentation

import edu.nextstep.wordle.application.wordle.WordResult
import edu.nextstep.wordle.application.wordle.WordResults

fun start() {
    println("WORDLE을 6번 만에 맞춰 보세요.\n" +
            "시도의 결과는 타일의 색 변화로 나타납니다.")
}

fun showAnswer(wordResults: WordResults) {
    println()

    val success = wordResults.isSuccess()
    if (success) {
        showSuccess(wordResults.wordResult.size)
    }

    showTiles(wordResults.wordResult)
}

private fun showSuccess(size: Int) {
    println("$size/6")
}

fun showTiles(wordResult: List<WordResult>) {
    for (word in wordResult.sortedBy { it.round }) {
        val sortedWindowResult = word.windowResults.sortedBy { it.position }
        val tiles = sortedWindowResult.joinToString(separator = "") { ColorAdapter.convert(it.match) }
        println(tiles)
    }
    println()
}

