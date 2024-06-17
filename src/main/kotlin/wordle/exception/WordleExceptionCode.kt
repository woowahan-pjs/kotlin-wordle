package wordle.exception

import wordle.domain.WORD_LENGTH

enum class WordleExceptionCode(val message: String) {
    WORD_NOT_ALLOW_SPACE("단어는 공백만 입력할 수 없습니다."),
    WORD_INVALID_LENGTH("단어의 길이는 ${WORD_LENGTH}자 입니다."),
    WORD_IS_NOT_IN_DICTIONARY("Wordle Game에서 유효한 단어가 아닙니다."),
}
