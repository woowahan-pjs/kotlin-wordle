package wordle.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import wordle.domain.Tile
import wordle.domain.Word
import wordle.domain.Words

class WordsTest {

    @Test
    fun `단어가 리스트 안에 존재하는지 확인해준다`() {
        val apple = Word("apple")
        val happy = Word("happy")
        val doggy = Word("doggy")
        val words = Words(listOf(apple, happy))

        words.contains(apple) shouldBe true
        words.contains(doggy) shouldBe false
    }

    @Test
    fun `단어가 정답인지 확인해준다`() {
        val words = Words(listOf(Word("apple")))

        words.isCorrect(Word("apple")) shouldBe true
        words.isCorrect(Word("happy")) shouldBe false
    }

    @Test
    fun `단어를 정답과 비교하여 결과 타일을 반환한다`() {
        val words = Words(listOf(Word("apple")))
        val word = Word("hello")

        val checkList = words.check(word)

        checkList shouldBe listOf(Tile.GRAY, Tile.YELLOW, Tile.GRAY, Tile.GREEN, Tile.GRAY)
    }
}
