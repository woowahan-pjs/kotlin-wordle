package wordle.domain

import java.nio.file.Files
import java.nio.file.Paths

class Words {

    companion object {
        private val value: List<String> = Files.readAllLines(Paths.get("src/main/resources/words.txt"))

        fun contains(word: String): Boolean {
            return value.contains(word)
        }
    }
}
