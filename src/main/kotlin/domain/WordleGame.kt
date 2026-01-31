package domain

import domain.Constant.SUCCESS_COUNT
import domain.vo.GameResult

class WordleGame() {
    fun logic(
        inputWord: GameString,
        answer: GameString,
    ): GameResult {
        val inputWordChars = inputWord.value.toMutableList()
        val answerChars = answer.value.toMutableList()
        val emojiArray = MutableList(inputWordChars.size) { Color.GREY.colorBox }
        val usedChars = MutableList(inputWordChars.size) { false }

        val greenCount = markGreen(inputWordChars, answerChars, emojiArray, usedChars)

        markYellow(inputWordChars, answerChars, emojiArray, usedChars)

        return GameResult(emojiArray, greenCount == SUCCESS_COUNT)
    }

    private fun markGreen(
        inputWordChars: MutableList<Char>,
        answerChars: MutableList<Char>,
        emojiArray: MutableList<String>,
        usedChars: MutableList<Boolean>,
    ): Int {
        var greenCount = 0
        for (i in inputWordChars.indices) {
            if (inputWordChars[i] == answerChars[i]) {
                emojiArray[i] = Color.GREEN.colorBox
                usedChars[i] = true
                greenCount++
            } else {
                emojiArray[i] = Color.GREY.colorBox
            }
        }
        return greenCount
    }

    private fun markYellow(
        inputWordChars: MutableList<Char>,
        answerChars: MutableList<Char>,
        emojiArray: MutableList<String>,
        usedChars: MutableList<Boolean>
    ) {
        for (i in inputWordChars.indices) {
            if (emojiArray[i] == Color.GREEN.colorBox) {
                continue
            }
            val j = answerChars.indices.firstOrNull {
                !usedChars[it] && answerChars[it] == inputWordChars[i]
            }
            if (j != null) {
                emojiArray[i] = Color.YELLOW.colorBox
                usedChars[j] = true
            }
        }
    }


}