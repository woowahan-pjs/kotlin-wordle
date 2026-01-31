package domain

data class GameString(val value: String) {
    init {
        require(value.length == Constant.LENGTH) {
            "길이가 ${Constant.LENGTH} 인 글자를 입력해주세요"
        }
    }
}