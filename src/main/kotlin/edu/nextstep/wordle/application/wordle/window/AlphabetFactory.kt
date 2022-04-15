package edu.nextstep.wordle.application.wordle.window

class AlphabetFactory(
    private val set: Set<Alphabet>,
) {
    fun findBy(alphabet: String): Alphabet {
        val target = Alphabet(alphabet)
        return set.first { it == target }
    }

    companion object {
        val instance = create()

        private fun create(): AlphabetFactory {
            val alphabets: Set<Alphabet> = ('A'..'Z').map { Alphabet(it.lowercase()) }
                .toSet()

            return AlphabetFactory(alphabets)
        }
    }
}
