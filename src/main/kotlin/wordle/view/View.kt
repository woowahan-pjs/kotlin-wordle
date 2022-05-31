package wordle.view

import wordle.domain.Mark
import wordle.domain.Results

fun printStartMessage(fixedCount: Int) {
    println("WORDLE 을 $fixedCount 번 만에 맞춰 보세요.\n시도의 결과는 타일의 색 변화로 나타납니다.")
}

fun requestInput(): String {
    println("정답을 입력해 주세요.")
    return readln()
}

fun printResults(results: Results, isPlaying: Boolean, tryCount: Int, fixedCount: Int) {
    println()
    if (!isPlaying) {
        printTryCount(tryCount, fixedCount)
    }
    results.value.forEach {
        printResult(it)
    }
    println()
}

fun printTryCount(tryCount: Int, fixedCount: Int) {
    println("$tryCount/$fixedCount\n")
}

private fun printResult(result: List<Mark>) {
    val stringBuilder = StringBuilder()
    result.forEach {
        when (it) {
            Mark.NONE -> stringBuilder.append("⬜")
            Mark.EXIST -> stringBuilder.append("🟨")
            Mark.EXACT -> stringBuilder.append("🟩")
        }
    }
    println(stringBuilder.toString())
}

fun printErrorMessage(message: String?) {
    println(message)
}
