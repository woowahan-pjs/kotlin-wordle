package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class GameTest : BehaviorSpec({
    val answer = Tiles.of("hello")

    Given("하나의 답안에") {
        val input = TestInput("hello")
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(repository)

        When("정답이 있을 경우") {
            val result = game.progress(input)

            then("게임이 종료 된다") {
                result.isCorrect() shouldBe true
            }
        }
    }

    Given("답안을 7번 이상") {
        val input = listOf("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde").map { TestInput(it) }
        val repository = TestWordsRepository(answer, setOf(Tiles(answer), Tiles.of("abcde")))
        val game = Game(repository)

        When("적으면") {
            input.forEach {
                game.progress(it)
            }

            Then("더이상 시도할 수 없다") {
                game.checkToRetry() shouldBe false
            }
        }
    }

    Given("존재하지 않는 단어는") {
        val input = listOf("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde").map { TestInput(it) }
        val repository = TestWordsRepository(answer, setOf(Tiles(answer)))
        val game = Game(repository)

        Then("예외를 발생시킨다") {
            input.forAll {
                shouldThrow<IllegalArgumentException> {
                    game.progress(it)
                }
            }
        }
    }
})

class TestInput(private val word: String) : Input {
    override fun read(): Tiles {
        return Tiles.of(word)
    }
}

class TestWordsRepository(val answer: Tiles, val words: Set<Tiles>) : Words {
    var tilesStack = mutableListOf<Tiles>()
    var calledTodayWords = false

    override fun exists(tiles: Tiles): Boolean {
        tilesStack.add(tiles)

        return words.contains(tiles)
    }

    override fun getTodayWords(): Tiles {
        calledTodayWords = true
        return answer
    }
}
