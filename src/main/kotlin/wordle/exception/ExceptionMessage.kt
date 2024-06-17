package wordle.exception

import wordle.domain.WORD_LENGTH

enum class ExceptionMessage(val message: String) {
    INVALID_LETTER("유효하지 않은 글자입니다."),
    WORD_NOT_BLANK("단어는 공백을 허용하지 않습니다."),
    INVALID_WORD_LENGTH("단어는 ${WORD_LENGTH}자 이여야만 합니다."),
    WORD_NOT_FOUND("단어사전에 없는 단어입니다."),
    TRY_COUNT_HAS_NOT_REMAINDER("시행 횟수는 0보다 작을 수 없습니다."),
    INVALID_WORD_RESULT_LENGTH("단어 비교 결과는 단어 길이($WORD_LENGTH)와 일치해야 합니다."),
}
