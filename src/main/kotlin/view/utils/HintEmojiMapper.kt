package view.utils

import domain.Hint

enum class HintEmojiMapper(val hintEmoji: String, val hint: Hint) {

    GRAY("⬜", Hint.GRAY),
    YELLOW("🟨", Hint.YELLOW),
    GREEN("🟩", Hint.GREEN),
    ;

    companion object {
        private const val CANNOT_FOUND_HINT_EMOJI_ERROR_MESSAGE = "해당하는 힌트 이모지를 찾을 수 없습니다."

        fun emojiMapping(hints: List<Hint>): String {
            return hints.joinToString("") { convertHintEmoji(it) }
        }

        private fun convertHintEmoji(hint: Hint): String {
            val emojiMapper = HintEmojiMapper.values().find {
                it.hint == hint
            } ?: throw IllegalArgumentException(CANNOT_FOUND_HINT_EMOJI_ERROR_MESSAGE)
            return emojiMapper.hintEmoji
        }
    }
}
