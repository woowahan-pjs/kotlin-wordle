package edu.nextstep.wordle.application.wordle.window

data class WindowResult(
    val position: Int,
    val match: Match,
) {
    fun update(match: Match): WindowResult {
        val updateMatch = if (this.match.updatable(match)) {
            match
        } else {
            this.match
        }

        return WindowResult(this.position, updateMatch)
    }
}
