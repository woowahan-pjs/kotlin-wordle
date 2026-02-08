package wordle.io

import wordle.domain.Round

interface Printer {
    fun introduce()

    fun requestInput()

    fun viewTile(tile: String)

    fun result(
        count: Int,
        round: Round,
        tile: String,
    )

    fun error(message: String)
}
