import domain.Tiles
import presentation.DefaultWordsRepository

fun main() {
    val repository = DefaultWordsRepository()

    println(repository.getTodayWords())
    println(repository.exists(Tiles.of("royal")))
}