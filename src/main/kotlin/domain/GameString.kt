package domain

data class GameString(val value: String) {
    init {
        require(value.length == Constant.WORD_LENGTH) {
            "길이가 ${Constant.WORD_LENGTH} 인 글자를 입력해주세요"
        }
    }
}