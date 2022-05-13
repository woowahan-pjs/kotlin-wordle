package wordle

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

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
}
