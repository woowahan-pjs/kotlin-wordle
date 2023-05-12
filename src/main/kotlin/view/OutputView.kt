package view

import domain.Hint
import view.utils.HintEmojiMapper

class OutputView {

    fun printWelcomeMessage() {
        println(
            "WORDLE을 6번 만에 맞춰 보세요.\n" +
                "시도의 결과는 타일의 색 변화로 나타납니다.",
        )
    }

    fun printTryCount(userTryCount: Int, maxTryCount: Int) {
        println("$userTryCount / $maxTryCount")
    }

    fun printHint(allHints: List<List<Hint>>) {
        for (hints in allHints) {
            println(HintEmojiMapper.emojiMapping(hints))
        }
    }

    fun printErrorMessage(errorMessage: String?) {
        println(errorMessage)
    }
}
