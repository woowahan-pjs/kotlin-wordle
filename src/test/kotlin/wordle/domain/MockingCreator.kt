package wordle.domain

import wordle.utils.Creator

class MockingCreator: Creator {

    override fun createWords(): Words {
        return Words(listOf(Word("queen"), Word("chunk"), Word("awake")))
    }
}
