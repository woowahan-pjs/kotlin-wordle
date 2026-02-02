package wordle.application

class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            GameMachine("words.txt", 6).start()
        }
    }
}
