package wordle.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class WordsReaderTest : FunSpec({

    test("파일을 읽어서 List를 반환해준다") {
        val words = WordsReader.getWords()

        words.size shouldBe 2309
    }
})
