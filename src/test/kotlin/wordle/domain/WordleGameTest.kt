package wordle.domain

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

private const val TODAY_WORD = "spill"
private val TODAY_WORD_OBJ = Word(TODAY_WORD)
private const val WORD1 = "hello"
private const val WORD2 = "label"
private const val WORD3 = "spell"
private const val BR = "\n"
private const val BLANK = ""
private const val WRONG_LENGTH_WORD = "word"
private const val INVALID_WORD = "abcde"

class WordleGameTest {
    @Test
    fun `6번 이내 오늘의 단어를 맞추면 성공 안내 문구를 출력한다`() {
        val printStream = System.out
        val outputStream = ByteArrayOutputStream()
        val inputStream = ByteArrayInputStream("$WORD1$BR$WORD2$BR$WORD3$BR$TODAY_WORD".toByteArray())

        System.setOut(PrintStream(outputStream))
        System.setIn(inputStream)

        WordleGame().play(TODAY_WORD_OBJ)

        System.setOut(printStream)
        System.setIn(System.`in`)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(
"""
🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩
🟩🟩🟩🟩🟩

성공입니다. 4 / 6
""".trimIndent(),
        )
    }

    @Test
    fun `6번 이내 오늘의 단어를 맞추지 못하면 실패 안내 문구와 오늘의 단어를 출력한다`() {
        val printStream = System.out
        val outputStream = ByteArrayOutputStream()
        val inputStream = ByteArrayInputStream("$WORD1$BR$WORD2$BR$WORD3$BR$WORD1$BR$WORD2$BR$WORD3".toByteArray())

        System.setOut(PrintStream(outputStream))
        System.setIn(inputStream)

        WordleGame().play(TODAY_WORD_OBJ)

        System.setOut(printStream)
        System.setIn(System.`in`)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(
"""
🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩
⬜⬜🟨🟩⬜

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩

🚀 정답을 입력하세요. : 
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩
⬜⬜🟨🟩⬜
🟨⬜⬜⬜🟩
🟩🟩⬜🟩🟩

오늘의 단어는 spill 입니다.
""".trimIndent(),
        )
    }

    @Test
    fun `답안 입력 시 공백을 입력하면 다시 입력을 받는다`() {
        val printStream = System.out
        val outputStream = ByteArrayOutputStream()
        val inputStream = ByteArrayInputStream("$BLANK$BR$TODAY_WORD".toByteArray())

        System.setOut(PrintStream(outputStream))
        System.setIn(inputStream)

        WordleGame().play(TODAY_WORD_OBJ)

        System.setOut(printStream)
        System.setIn(System.`in`)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(
"""
🚀 정답을 입력하세요. : 
🥲 다시 시도하세요! : 단어는 공백을 허용하지 않습니다.

🚀 정답을 입력하세요. : 
🟩🟩🟩🟩🟩

성공입니다. 1 / 6
""".trimIndent(),
        )
    }

    @Test
    fun `답안 입력 시 정해진 단어의 길이와 다른 단어를 입력하면 다시 입력을 받는다`() {
        val printStream = System.out
        val outputStream = ByteArrayOutputStream()
        val inputStream = ByteArrayInputStream("$WRONG_LENGTH_WORD$BR$TODAY_WORD".toByteArray())

        System.setOut(PrintStream(outputStream))
        System.setIn(inputStream)

        WordleGame().play(TODAY_WORD_OBJ)

        System.setOut(printStream)
        System.setIn(System.`in`)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(
"""
🚀 정답을 입력하세요. : 
🥲 다시 시도하세요! : 단어는 5자 이여야만 합니다.

🚀 정답을 입력하세요. : 
🟩🟩🟩🟩🟩

성공입니다. 1 / 6
""".trimIndent(),
        )
    }

    @Test
    fun `답안 입력 시 단어 사전에 없는 단어를 입력하면 다시 입력을 받는다`() {
        val printStream = System.out
        val outputStream = ByteArrayOutputStream()
        val inputStream = ByteArrayInputStream("$INVALID_WORD$BR$TODAY_WORD".toByteArray())

        System.setOut(PrintStream(outputStream))
        System.setIn(inputStream)

        WordleGame().play(TODAY_WORD_OBJ)

        System.setOut(printStream)
        System.setIn(System.`in`)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(
"""
🚀 정답을 입력하세요. : 
🥲 다시 시도하세요! : 단어사전에 없는 단어입니다.

🚀 정답을 입력하세요. : 
🟩🟩🟩🟩🟩

성공입니다. 1 / 6
""".trimIndent(),
        )
    }
}
