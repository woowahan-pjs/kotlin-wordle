import domain.Game
import infra.DefaultWordsRepository
import presentation.DefaultInput
import presentation.DefaultOutput

fun main() {
    val input = DefaultInput()
    val output = DefaultOutput()
    val repository = DefaultWordsRepository()

    val game = Game(input, output, repository)

    game.start()
}