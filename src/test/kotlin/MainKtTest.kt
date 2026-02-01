import domain.Color
import domain.GameString
import domain.WordleGame
import org.assertj.core.api.Assertions

import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun `정답을 맞추면 모두 초록색이다()`() {
        val input = GameString("under")
        val answer = GameString("under")
        val game = WordleGame()
        val expected = listOf(
            Color.GREEN,
            Color.GREEN,
            Color.GREEN,
            Color.GREEN,
            Color.GREEN
        )

        val result = game.logic(input, answer).emojiArray

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `입력문자열이 answer의 글자에 포함되어있으면 yellow`() {
        val input = GameString("uooon")
        val answer = GameString("under")
        val game = WordleGame()
        val expected = listOf(
            Color.GREEN,
            Color.GREY,
            Color.GREY,
            Color.GREY,
            Color.YELLOW
        )

        val result = game.logic(input, answer).emojiArray

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `정답글자를 중복으로 입력할 경우 grey`() {
        val input = GameString("uuooo")
        val answer = GameString("under")
        val game = WordleGame()
        val expected = listOf(
            Color.GREEN,
            Color.GREY,
            Color.GREY,
            Color.GREY,
            Color.GREY
        )

        val result = game.logic(input, answer).emojiArray

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `정답 글자가 중복일 경우, 정답 개수만큼 Green, Yellow`() {
        val input = GameString("uuuuo")
        val answer = GameString("undeu")
        val game = WordleGame()
        val expected = listOf(
            Color.GREEN,
            Color.YELLOW,
            Color.GREY,
            Color.GREY,
            Color.GREY
        )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `정답 글자 수만큼 Yellow 추가 확인`() {
        val input = GameString("ououo")
        val answer = GameString("unueu")
        val game = WordleGame()
        val expected = listOf(
            Color.GREY,
            Color.YELLOW,
            Color.GREY,
            Color.YELLOW,
            Color.GREY
        )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `정답 글자 수만큼 Yellow 추가 확인2`() {
        val input = GameString("unueu")
        val answer = GameString("ououo")
        val game = WordleGame()
        val expected = listOf(
            Color.YELLOW,
            Color.GREY,
            Color.YELLOW,
            Color.GREY,
            Color.GREY
        )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `글자 모두 정답에 있는데 순서가 다르면 노란색`() {
        val input = GameString("abcde")
        val answer = GameString("badec")
        val game = WordleGame()
        val expected = listOf(
            Color.YELLOW,
            Color.YELLOW,
            Color.YELLOW,
            Color.YELLOW,
            Color.YELLOW
        )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }
}
