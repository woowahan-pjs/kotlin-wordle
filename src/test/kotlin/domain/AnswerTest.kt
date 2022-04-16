package domain

import domain.MatchResult.CORRECT
import domain.MatchResult.INCORRECT
import domain.MatchResult.MISSING
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class AnswerTest : ShouldSpec({
    context("Answer는") {
        val answer = Answer.of("hello")

        context("5개 Tile 로") {
            val tiles = Tiles.of("hello")
            should("구성되어 있다") {
                answer.tiles shouldBe tiles
            }
        }

        context("5개가 아닌 Tile 은") {
            should("실패한다") {
                forAll(
                    table(
                        headers("word"),
                        row(""),
                        row("h"),
                        row("hell"),
                        row("hellow"),
                    )
                ) { word ->
                    shouldThrow<IllegalArgumentException> { Answer.of(word) }
                }
            }
        }

        context("답안과 비교하여") {

            should("같은 위치에 있으면 CORRECT이다") {
                val tiles = Tiles.of("hello")
                val matches = answer.match(tiles)
                matches.results shouldContainExactly listOf(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT)
            }

            should("다른 위치에 있으면 MISSING이다") {
                val tiles = Tiles.of("olehl")
                val matches = answer.match(tiles)
                matches.results shouldContain MISSING
            }

            should("없을 경우 INCORRECT이다") {
                val tiles = Tiles.of("zzzzz")
                val matches = answer.match(tiles)
                matches.results shouldContain INCORRECT
            }

            should("맞는 부분을 CORRECT로 변경하고 나머지 존재하는 부분을 MISSING으로 변경된다 ") {
                val tiles = Tiles.of("olleh")
                val matches = answer.match(tiles)
                matches.results shouldContainExactly listOf(MISSING, MISSING, CORRECT, MISSING, MISSING)
            }

            should("정답이 뒤에 있으면 정답부터 CORRECT으로 변경된다") {
                val tiles = Tiles.of("lllll")
                val matches = answer.match(tiles)
                matches.results shouldContainExactly listOf(INCORRECT, INCORRECT, CORRECT, CORRECT, INCORRECT)
            }

            should("답안에 있는 타일이 정답 개수보다 많으면 겹치는 부분을 제외한 부분이 INCORRECT으로 변경된다") {
                val tiles = Tiles.of("llool")
                val matches = answer.match(tiles)
                matches.results shouldContainExactly listOf(MISSING, MISSING, MISSING, INCORRECT, INCORRECT)
            }
        }
    }
})
