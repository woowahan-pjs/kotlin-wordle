package wordle.view

import wordle.domain.Mark
import wordle.domain.Results

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

fun printTryCount(tryCount: Int) {
    println("$tryCount/6\n")
}

fun printResults(results: Results) {
    println()
    results.value.forEach {
        printResult(it)
    }
    println()
}

// 수정 가능 할 지도?
private fun printResult(result: List<Mark>) {
    val stringBuilder = StringBuilder()
    result.forEach {
        when (it) {
            Mark.NONE -> stringBuilder.append("⬜")
            Mark.EXIST -> stringBuilder.append("\uD83D\uDFE8")
            Mark.EXACT -> stringBuilder.append("\uD83D\uDFE9")
        }
    }
    println(stringBuilder.toString())
}
