import domain.Game
import domain.PlayResult
import infra.DefaultWordsPool
import presentation.printAll
import presentation.read

fun main() {
    val game = Game(DefaultWordsPool())

    do {
        if (game.play(read()) == PlayResult.SUCCEEDED) {
            printAll(game)
        }
    } while (game.isPlaying())
}
