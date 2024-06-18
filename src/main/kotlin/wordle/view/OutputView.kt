package wordle.view

import wordle.domain.LetterMatch
import wordle.domain.MAX_TRY_COUNT
import wordle.domain.Word
import wordle.domain.WordResults

private const val ENTER = "\n"
private const val SPACE = ""

fun printStartingGameMessage() {
    println("🎮 WORDLE을 ${MAX_TRY_COUNT}번 만에 맞춰 보세요.$ENTER📌 시도의 결과는 타일의 색 변화로 나타납니다.🥳$ENTER")
}

fun printWordResults(results: WordResults) {
    println(results.wordResults().joinToString(ENTER) { printTiles(it) } + ENTER)
}

fun printRetry(message: String?) {
    println("🥲 다시 시도하세요! : ${message ?: SPACE}$ENTER")
}

fun printSuccess(attemptCount: Int) {
    println("🎉 성공입니다. $attemptCount / $MAX_TRY_COUNT")
}

fun printFail(todayWord: Word) {
    println("👻 실패하였습니다. 오늘의 단어는 [ ${todayWord.letters()} ] 입니다.")
}

private fun printTiles(it: List<LetterMatch>) = it.joinToString(SPACE) { Tile.of(it).color }
