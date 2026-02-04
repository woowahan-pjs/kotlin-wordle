package wordle.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class WordsTest : FunSpec({

    test("단어가 리스트 안에 존재하는지 확인해준다") {
        val apple = Word("apple")
        val happy = Word("happy")
        val doggy = Word("doggy")
        val words = Words(listOf(apple, happy))

        words.contains(apple) shouldBe true
        words.contains(doggy) shouldBe false
    }

    test("단어가 정답인지 확인해준다") {
        val words = Words(listOf(Word("apple")))

        words.isCorrect(Word("apple")) shouldBe true
        words.isCorrect(Word("happy")) shouldBe false
    }

    test("단어를 정답과 비교하여 결과 타일을 반환한다") {
        io.kotest.data.forAll(
            table(
                headers("answer", "InputWord", "expected"),
                row(
                    Word("apple"), Word("hello"),
                    listOf(Tile.GRAY, Tile.YELLOW, Tile.GRAY, Tile.GREEN, Tile.GRAY)
                ),
                row(
                    Word("patty"), Word("happy"),
                    listOf(Tile.GRAY, Tile.GREEN, Tile.YELLOW, Tile.GRAY, Tile.GREEN)
                ),
                row(
                    Word("vappr"), Word("hapyp"),
                    listOf(Tile.GRAY, Tile.GREEN, Tile.GREEN, Tile.GRAY, Tile.YELLOW)
                )
            )
        ) { answer: Word, inputWord: Word, expected: List<Tile> ->
            val words = Words(listOf(answer))

            words.check(inputWord) shouldBe expected
        }
    }
})
