package wordle.application

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.LocalDate

private val GAME_START_DATE = LocalDate.of(2021, 6, 20)

class WordleGameTest {
    @Test
    fun `6번 이내 오늘의 단어를 맞추면 성공 안내 문구를 출력한다`() {
        val inputAnswerWords = successConsoleInput
        val (printStream, outputStream) = printStreamByteArrayOutputStreamPair(inputAnswerWords)

        WordleGame(GAME_START_DATE).play()
        printStream(printStream)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(successConsolePrint.trimIndent())
    }

    @Test
    fun `6번 이내 오늘의 단어를 맞추지 못하면 실패 안내 문구와 오늘의 단어를 출력한다`() {
        val inputAnswerWords = failConsoleInput
        val (printStream, outputStream) = printStreamByteArrayOutputStreamPair(inputAnswerWords)

        WordleGame(GAME_START_DATE).play()
        printStream(printStream)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(failConsolePrint.trimIndent())
    }

    @Test
    fun `답안 입력 시 공백을 입력하면 다시 입력을 받는다`() {
        val inputAnswerWords = "$blank$br$wordSpill"
        val (printStream, outputStream) = printStreamByteArrayOutputStreamPair(inputAnswerWords)

        WordleGame(GAME_START_DATE).play()
        printStream(printStream)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(blankConsolePrint.trimIndent())
    }

    @Test
    fun `답안 입력 시 정해진 단어의 길이와 다른 단어를 입력하면 다시 입력을 받는다`() {
        val inputAnswerWords = "$invalidLengthWord$br$wordSpill"
        val (printStream, outputStream) = printStreamByteArrayOutputStreamPair(inputAnswerWords)

        WordleGame(GAME_START_DATE).play()
        printStream(printStream)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(invalidLengthConsolePrint.trimIndent())
    }

    @Test
    fun `답안 입력 시 단어 사전에 없는 단어를 입력하면 다시 입력을 받는다`() {
        val inputAnswerWords = "$invalidWord$br$wordSpill"
        val (printStream, outputStream) = printStreamByteArrayOutputStreamPair(inputAnswerWords)

        WordleGame(GAME_START_DATE).play()
        printStream(printStream)

        assertThat(outputStream.toString().trimIndent()).isEqualTo(invalidWordConsolePrint.trimIndent())
    }

    private fun printStreamByteArrayOutputStreamPair(inputAnswerWords: String): Pair<PrintStream, ByteArrayOutputStream> {
        val printStream = System.out
        val outputStream = ByteArrayOutputStream()
        val inputStream = ByteArrayInputStream(inputAnswerWords.toByteArray())

        System.setOut(PrintStream(outputStream))
        System.setIn(inputStream)

        return Pair(printStream, outputStream)
    }

    private fun printStream(printStream: PrintStream) {
        System.setOut(printStream)
        System.setIn(System.`in`)
    }

    private val answerWordHello = "hello"
    private val answerWordLabel = "label"
    private val answerWordSpell = "spell"
    private val wordSpill = "spill"
    private val br = "\n"
    private val blank = ""
    private val invalidLengthWord = "word"
    private val invalidWord = "abcde"
    private val successConsoleInput = "$answerWordHello$br$answerWordLabel$br$answerWordSpell$br$wordSpill"
    private val failConsoleInput =
        "$answerWordHello$br$answerWordLabel$br$answerWordSpell$br$answerWordHello$br$answerWordLabel$br$answerWordSpell"

    private val successConsolePrint =
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
        
        🎉 성공입니다. 4 / 6
        """

    private val failConsolePrint =
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
        
        👻 실패하였습니다. 오늘의 단어는 [ spill ] 입니다.
        """

    private val blankConsolePrint =
        """
        🚀 정답을 입력하세요. : 
        🥲 다시 시도하세요! : 단어는 공백만 입력할 수 없습니다.
        
        🚀 정답을 입력하세요. : 
        🟩🟩🟩🟩🟩
        
        🎉 성공입니다. 1 / 6
        """

    private val invalidLengthConsolePrint =
        """
        🚀 정답을 입력하세요. : 
        🥲 다시 시도하세요! : 단어의 길이는 5자 입니다.
        
        🚀 정답을 입력하세요. : 
        🟩🟩🟩🟩🟩
        
        🎉 성공입니다. 1 / 6
        """

    private val invalidWordConsolePrint =
        """
        🚀 정답을 입력하세요. : 
        🥲 다시 시도하세요! : Wordle Game에서 유효한 단어가 아닙니다.
        
        🚀 정답을 입력하세요. : 
        🟩🟩🟩🟩🟩
        
        🎉 성공입니다. 1 / 6
        """
}
