package edu.nextstep.wordle.application.wordle.dictionary

import edu.nextstep.wordle.application.wordle.Word

fun interface WordFinder {
    fun contain(input: Word): Boolean
}
