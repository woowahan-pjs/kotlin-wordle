package wordle.io

/**
 * 외부로부터 입력을 받는 객체입니다.
 */
class ConsoleScanner : Scanner {
    /**
     * 값을 입력 받습니다.
     *
     * 입력 값은 필수이며, 받은 값은 소문자로 치환하며 반환합니다.
     */
    override fun input(): String {
        val input = readlnOrNull()?.trim() ?: ""

        require(input != "" && input.isNotBlank()) { "입력값을 확인해주세요." }

        return input.lowercase()
    }
}

/**
 * 기존에는 readLine()을 이용해 입력 받았는데, readOrNull()이라는 메서드도 존재하여 차이점이 무엇일까 찾아 봤습니다.
 *
 * - readLine() : 결과가 null이면 명시적 예외를 던지는 형식
 * - readOrNull() : 내부적으로 readLine()을 호출하지만, EOF면 null을 반환하고, 예외를 던지지 않는 형식
 */
