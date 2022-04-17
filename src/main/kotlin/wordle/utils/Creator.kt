package wordle.utils

import wordle.domain.Words

interface Creator {

    fun createWords(): Words
}
