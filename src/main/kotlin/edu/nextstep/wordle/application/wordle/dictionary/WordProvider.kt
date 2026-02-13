package edu.nextstep.wordle.application.wordle.dictionary

import edu.nextstep.wordle.application.wordle.Word
import java.time.LocalDate

interface WordProvider {
    fun provide(date: LocalDate = LocalDate.now()): Word
}
