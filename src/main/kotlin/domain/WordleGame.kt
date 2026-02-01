package domain

import domain.vo.GameResult

class WordleGame {
    fun logic(inputWord: GameString, answer: GameString): GameResult {
        val inputWordChars = inputWord.value.toMutableList()
        val answerChars = answer.value.toMutableList()
        val emojiArray = MutableList(inputWordChars.size) { Color.GREY }
        val usedChars = MutableList(inputWordChars.size) { false }

        val greenCount = checkGreen(inputWordChars, answerChars, emojiArray, usedChars)

        checkYellow(inputWordChars, answerChars, emojiArray, usedChars)

        return GameResult(emojiArray, greenCount == 5)
    }

    private fun checkGreen(
        inputWordChars: MutableList<Char>,
        answerChars: MutableList<Char>,
        emojiArray: MutableList<Color>,
        usedChars: MutableList<Boolean>
    ): Int {
        var greenCount = 0
        for (i in inputWordChars.indices) {
            if (inputWordChars[i] == answerChars[i]) {
                emojiArray[i] = Color.GREEN
                usedChars[i] = true
                greenCount++
            } else {
                emojiArray[i] = Color.GREY
            }
        }
        return greenCount
    }

    private fun checkYellow(
        inputWordChars: MutableList<Char>,
        answerChars: MutableList<Char>,
        emojiArray: MutableList<Color>,
        usedChars: MutableList<Boolean>
    ) {
        for (i in inputWordChars.indices) {
            if (emojiArray[i] == Color.GREEN) {
                continue
            }
            val j = answerChars.indices.firstOrNull {
                !usedChars[it] && answerChars[it] == inputWordChars[i]
            }
            if (j != null) {
                emojiArray[i] = Color.YELLOW
                usedChars[j] = true
            }
        }
    }


}