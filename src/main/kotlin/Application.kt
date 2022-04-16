import domain.Game
import infra.DefaultWordsPool
import presentation.DefaultInput
import presentation.DefaultOutput

fun main() {
    val input = DefaultInput()
    val output = DefaultOutput()
    val repository = DefaultWordsPool()

    val game = Game(input, output, repository)

    game.start()
}
