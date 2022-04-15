package edu.nextstep.wordle.application.wordle.dictionary

fun interface WordFinder {
    fun contain(input: String): Boolean
}
