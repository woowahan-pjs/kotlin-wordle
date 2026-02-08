package wordle.domain

class WordFactory {
    companion object {
        fun create(
            validator: WordValidator,
            input: String,
        ): Word {
            validator.validate(input)

            return Word(input)
        }
    }
}
