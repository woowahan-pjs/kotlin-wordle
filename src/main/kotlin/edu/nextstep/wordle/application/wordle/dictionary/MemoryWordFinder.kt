package edu.nextstep.wordle.application.wordle.dictionary

import edu.nextstep.wordle.application.wordle.Word

class MemoryWordFinder(
    val words: List<Word>,
) : WordFinder {
    override fun contain(input: Word): Boolean {
        return words.contains(input)
    }
}
