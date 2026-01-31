import domain.Color
import domain.GameString
import domain.WordleGame
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MainKtTest {
    @Test
    fun `정답을 맞추면 모두 초록색이다`() {
        val input = GameString("under")
        val answer = GameString("under")
        val game = WordleGame()
        val expected =
            listOf(
                Color.GREEN.colorBox,
                Color.GREEN.colorBox,
                Color.GREEN.colorBox,
                Color.GREEN.colorBox,
                Color.GREEN.colorBox,
            )

        val result = game.logic(input, answer).emojiArray

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `입력문자열이 answer의 글자에 포함되어있으면 yellow`() {
        val input = GameString("uooon")
        val answer = GameString("under")
        val game = WordleGame()
        val expected =
            listOf(
                Color.GREEN.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
                Color.YELLOW.colorBox,
            )

        val result = game.logic(input, answer).emojiArray

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `정답글자를 중복으로 입력할 경우 grey`() {
        val input = GameString("uuooo")
        val answer = GameString("under")
        val game = WordleGame()
        val expected =
            listOf(
                Color.GREEN.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
            )

        val result = game.logic(input, answer).emojiArray

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `같은 글자가 중복 입력될 경우 Green, Yellow를 적용하고 나머지 단어는 Grey`() {
        val input = GameString("uuuuo")
        val answer = GameString("undeu")
        val game = WordleGame()
        val expected =
            listOf(
                Color.GREEN.colorBox,
                Color.YELLOW.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
            )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `중복된 글자가 입력되고 위치만 다를 경우 Yellow로 표현한다 1`() {
        val input = GameString("ououo")
        val answer = GameString("unueu")
        val game = WordleGame()
        val expected =
            listOf(
                Color.GREY.colorBox,
                Color.YELLOW.colorBox,
                Color.GREY.colorBox,
                Color.YELLOW.colorBox,
                Color.GREY.colorBox,
            )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `중복된 글자가 입력되고 위치만 다를 경우 Yellow로 표현한다 2`() {
        val input = GameString("unueu")
        val answer = GameString("ououo")
        val game = WordleGame()
        val expected =
            listOf(
                Color.YELLOW.colorBox,
                Color.GREY.colorBox,
                Color.YELLOW.colorBox,
                Color.GREY.colorBox,
                Color.GREY.colorBox,
            )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `글자는 모두 포함되지만 위치가 맞지 않다면 모두 Yellow`() {
        val input = GameString("abcde")
        val answer = GameString("badec")
        val game = WordleGame()
        val expected =
            listOf(
                Color.YELLOW.colorBox,
                Color.YELLOW.colorBox,
                Color.YELLOW.colorBox,
                Color.YELLOW.colorBox,
                Color.YELLOW.colorBox,
            )

        val result = game.logic(input, answer).emojiArray
        Assertions.assertThat(result).isEqualTo(expected)
    }
}
